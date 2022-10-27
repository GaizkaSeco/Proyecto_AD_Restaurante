package InterfazGrafica;

import Clases.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaClientes extends JFrame {
    private JPanel panelClientes;
    private JTable table1;
    private JButton anadirBoton;
    private JButton eliminarBoton;
    private JButton editarBoton;
    private JButton reloadBoton;
    private JButton atrasBoton;
    public List<Cliente> datos = new ArrayList<Cliente>();

    public VentanaClientes() {
        setContentPane(panelClientes);
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
                JFrame frame = new CrearCliente(datos);
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
                    eliminarCliente(id);
                }
            }
        });
        editarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Para editar deves seleccionar en la tabla.");
                } else {
                    int id = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 0).toString());
                    cargarDatos();
                    JFrame frame = new EditarCliente(id, datos);
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
            File file = new File("Clientes.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            datos.clear();
            Cliente cliente;
            while ((cliente = (Cliente) fileobj.readObject()) != null) {
                datos.add(cliente);
            }
            fileobj.close();
        } catch (EOFException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Ha surgido un error al intentar acceder al los datos.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ha surgido un error inesperado.");
        }
    }

    public void modificarTabla() {
        cargarDatos();
        String[] nombreColumnas = {"id", "Nombre", "Telefono", "Email"};
        int cantidad = datos.size();
        String[][] d = new String[cantidad][4];
        for (int i = 0; i < datos.size(); i++) {
            d[i][0] = String.valueOf(datos.get(i).getId());
            d[i][1] = String.valueOf(datos.get(i).getNombre());
            d[i][2] = String.valueOf(datos.get(i).getTelefono());
            d[i][3] = String.valueOf(datos.get(i).getEmail());
        }
        table1.setModel(new DefaultTableModel(d, nombreColumnas));
    }

    public void eliminarCliente(int id) {
        cargarDatos();
        List<Cliente> nuevos = new ArrayList<>();
        for (Cliente dato : datos) {
            if (dato.getId() != id) {
                nuevos.add(dato);
            }
        }

        try {
            ObjectOutputStream fileobj = new ObjectOutputStream(new FileOutputStream("Clientes.dat"));

            for (Cliente dato : nuevos) {
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
