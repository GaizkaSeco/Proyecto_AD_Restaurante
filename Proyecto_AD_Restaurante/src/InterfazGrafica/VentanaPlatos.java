package InterfazGrafica;

import Clases.Plato;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaPlatos extends JFrame{
    private JPanel PanelPlatos;
    private JButton anadirBoton;
    private JButton eliminarBoton;
    private JTable table1;
    private JButton reloadBoton;
    private JButton atrasBoton;
    List<Plato> datos = new ArrayList<Plato>();

    public VentanaPlatos() {
        modificarTabla();
        setContentPane(PanelPlatos);
        anadirBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
                JFrame frame = new CrearPlato(datos);
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        eliminarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Para eliminar deves seleccionar en la tabla.");
                } else {
                    int id = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 0).toString());
                    eliminarPlato(id);
                }
            }
        });
        atrasBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Principal();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
    }

    public void cargarDatos() {
        try {
            File file = new File("Platos.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            datos.clear();
            Plato plato;
            while ((plato = (Plato) fileobj.readObject()) != null) {
                datos.add(plato);
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
        String[] nombreColumnas = {"id", "Plato", "Descripcion", "Coste", "Categoria"};
        int cantidad = datos.size();
        String[][] d = new String[cantidad][6];
        for (int i = 0; i < datos.size(); i++) {
            d[i][0] = String.valueOf(datos.get(i).getId());
            d[i][1] = String.valueOf(datos.get(i).getNombre());
            d[i][2] = String.valueOf(datos.get(i).getDescripcion());
            d[i][3] = String.valueOf(datos.get(i).getPrecio());
            d[i][4] = String.valueOf(datos.get(i).getCategoria());
        }
        table1.setModel(new DefaultTableModel(d, nombreColumnas));
    }

    public void eliminarPlato(int id) {
        cargarDatos();
        List<Plato> nuevos = new ArrayList<>();
        for (Plato dato : datos) {
            if (dato.getId() != id) {
                //preguntar si tiene sentido que no al eliminar no se cambien los id o es mejor que no alla ids vacios
                nuevos.add(dato);
            }
        }

        try {
            ObjectOutputStream fileobj = new ObjectOutputStream(new FileOutputStream("Platos.dat"));

            for (Plato dato : nuevos) {
                fileobj.writeObject(dato);
            }

            fileobj.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo de datos.");
        } catch (IOException e) {
            System.out.println("");
        }

        modificarTabla();
    }
}
