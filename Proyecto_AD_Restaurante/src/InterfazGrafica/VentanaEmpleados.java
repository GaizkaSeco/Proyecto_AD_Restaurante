package InterfazGrafica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Clases.*;

public class VentanaEmpleados extends JFrame {
    private JPanel PanelEmpleados;
    private JTable table1;
    private JButton anadirBoton;
    private JButton eliminarBoton;
    private JButton editarBoton;
    private JButton atrasBoton;
    private JButton reloadBoton;
    public List<Empleado> datos = new ArrayList<Empleado>();

    public VentanaEmpleados() {
        setContentPane(PanelEmpleados);
        modificarTabla();

        reloadBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cargarDatos();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                modificarTabla();
            }
        });
        anadirBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new CrearEmpleado();
                frame.setSize(500, 300);
                frame.setVisible(true);
            }
        });
        eliminarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 0).toString());
                eliminarEmpleado(id);
            }
        });
    }

    public synchronized void cargarDatos() throws IOException {
        try {
            File file = new File("Empleados.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            datos.clear();
            Empleado empleado = (Empleado) fileobj.readObject();
            while (empleado != null) {
                datos.add(empleado);
                empleado = (Empleado) fileobj.readObject();
            }
            fileobj.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No es posible leer los datos del los empleados.");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Ha surgido un error al intentar acceder al los datos.");
        }
    }

    public synchronized void modificarTabla() {
        try {
            cargarDatos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] nombreColumnas = {"id", "Nombre", "Salario", "Fecha Contrato", "Telefono", "Email"};
        int cantidad = datos.size();
        String[][] d = new String[cantidad][6];
        try {
            ObjectInputStream fileobj = new ObjectInputStream(new FileInputStream("Empleados.dat"));
            Empleado empleado = (Empleado) fileobj.readObject();
            int i = 0;
            int j = 0;
            while (empleado != null) {
                d[i][j] = String.valueOf(empleado.getId());
                j++;
                d[i][j] = empleado.getNombre();
                j++;
                d[i][j] = empleado.getSalario().toString();
                j++;
                d[i][j] = empleado.getFechaCon();
                j++;
                d[i][j] = String.valueOf(empleado.getTelefono());
                j++;
                d[i][j] = empleado.getEmail();
                j = 0;
                i++;
                empleado = null;
            }
            fileobj.close();
            table1.setModel(new DefaultTableModel(d, nombreColumnas));
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ew) {
            JOptionPane.showMessageDialog(null, "Ha surgido un error al intentar acceder al los datos.");
        }
    }

    public synchronized void eliminarEmpleado(int id) {
        try {
            cargarDatos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Empleado> nuevos = new ArrayList<>();
        for (Empleado dato : datos) {
            if (dato.getId() != id) {
                //preguntar si tiene sentido que no al eliminar no se cambien los id o es mejor que no alla ids vacios
                nuevos.add(dato);
            }
        }

        try {
            ObjectOutputStream fileobj = new ObjectOutputStream(new FileOutputStream("Empleados.dat"));

            for (Empleado dato : nuevos) {
                fileobj.writeObject(dato);
            }

            fileobj.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        modificarTabla();
    }
}
