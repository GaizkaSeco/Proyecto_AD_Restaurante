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
                cargarDatos();
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

    public void cargarDatos() {
        try {
            File file = new File("Empleados.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            datos.clear();
            Empleado empleado;
            while ((empleado = (Empleado) fileobj.readObject()) != null) {
                datos.add(empleado);
            }
            fileobj.close();
        } catch (IOException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Ha surgido un error al intentar acceder al los datos.");
        }
    }

    public void modificarTabla() {
        cargarDatos();
        String[] nombreColumnas = {"id", "Nombre", "Salario", "Fecha Contrato", "Telefono", "Email"};
        int cantidad = datos.size();
        String[][] d = new String[cantidad][6];
        for (int i = 0; i < datos.size(); i++) {
            d[i][0] = String.valueOf(datos.get(i).getId());
            d[i][1] = String.valueOf(datos.get(i).getNombre());
            d[i][2] = String.valueOf(datos.get(i).getSalario());
            d[i][3] = String.valueOf(datos.get(i).getFechaCon());
            d[i][4] = String.valueOf(datos.get(i).getTelefono());
            d[i][5] = String.valueOf(datos.get(i).getEmail());
        }
        table1.setModel(new DefaultTableModel(d, nombreColumnas));
    }

    public void eliminarEmpleado(int id) {
        cargarDatos();
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
