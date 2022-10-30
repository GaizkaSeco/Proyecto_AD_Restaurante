package InterfazGrafica;

import Clases.Cliente;
import Clases.Empleado;
import Clases.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaAlmacen extends JFrame {
    private JPanel panelAlmacen;
    private JTable table1;
    private JButton atrasBoton;
    private JButton anadirBoton;
    private JButton eliminarBoton;
    private JButton editarButton;
    private JButton reloadBoton;
    public List<Producto> datos = new ArrayList<Producto>();

    public VentanaAlmacen() {
        setContentPane(panelAlmacen);
        modificarTabla();
        reloadBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarTabla();
            }
        });
        anadirBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
                JFrame frame = new AnadirProducto(datos);
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        eliminarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Para eliminar debes seleccionar en la tabla.");
                } else {
                    int id = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 0).toString());
                    eliminarProducto(id);
                }
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Para editar debes seleccionar en la tabla.");
                } else {
                    int id = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 0).toString());
                    cargarDatos();
                    JFrame frame = new EditarProducto(datos, id);
                    frame.setSize(500, 300);
                    frame.setVisible(true);
                    dispose();
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
            File file = new File("Productos.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            datos.clear();
            Producto producto;
            while ((producto = (Producto) fileobj.readObject()) != null) {
                datos.add(producto);
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
        String[] nombreColumnas = {"id", "Nombre", "Cantidad"};
        int cantidad = datos.size();
        String[][] d = new String[cantidad][3];
        for (int i = 0; i < datos.size(); i++) {
            d[i][0] = String.valueOf(datos.get(i).getId());
            d[i][1] = String.valueOf(datos.get(i).getProducto());
            d[i][2] = String.valueOf(datos.get(i).getCantidad());
        }
        table1.setModel(new DefaultTableModel(d, nombreColumnas));
    }

    public void eliminarProducto(int id) {
        cargarDatos();
        List<Producto> nuevos = new ArrayList<>();
        for (Producto dato : datos) {
            if (dato.getId() != id) {
                nuevos.add(dato);
            }
        }

        try {
            ObjectOutputStream fileobj = new ObjectOutputStream(new FileOutputStream("Productos.dat"));

            for (Producto dato : nuevos) {
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
