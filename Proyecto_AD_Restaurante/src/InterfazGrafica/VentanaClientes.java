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
        //Mostramos el panel de la interfaz grafica
        setContentPane(panelClientes);
        //llamamos a la funcion para cargar los datos en la tabla
        modificarTabla();
        //Listener del boton de volver a cargar los datos por si es necesatio volver a actulalizar la tabla
        reloadBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarTabla();
            }
        });
        //Listener del boton de añadir cliente que carga la ventana de añadir cliente
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
        //Listener del boton de aliminar el cual le manda a una funcion el id del elemento seleccionado en la tabla el cual se quiere eliminar
        eliminarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Para eliminar debes seleccionar en la tabla.");
                } else {
                    int id = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 0).toString());
                    eliminarCliente(id);
                }
            }
        });
        //Listener del boton de de editar el cual manda el id y los datos cargados a la ventana de editar
        editarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Para editar debes seleccionar en la tabla.");
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
        //listener del boton de atras el cual vuelve a la ventana principal
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

    //carga los datos del .DAT a un array para trabajaro mejor cone ellos
    public void cargarDatos() {
        try {
            //se crea el flujo de salida
            File file = new File("Clientes.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            //cargamos los datos al array
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
        //Nombre de las columnas y cargamos los datos al array que se le van a enviar al la tabla para cargar los datos
        String[] nombreColumnas = {"id", "Nombre", "Telefono", "Email"};
        int cantidad = datos.size();
        String[][] d = new String[cantidad][4];
        for (int i = 0; i < datos.size(); i++) {
            d[i][0] = String.valueOf(datos.get(i).getId());
            d[i][1] = String.valueOf(datos.get(i).getNombre());
            d[i][2] = String.valueOf(datos.get(i).getTelefono());
            d[i][3] = String.valueOf(datos.get(i).getEmail());
        }
        //se carga el modelo de la tabla
        table1.setModel(new DefaultTableModel(d, nombreColumnas));
    }

    //Funcion para eliminar el cliente seleccionado
    public void eliminarCliente(int id) {
        cargarDatos();
        //Se recorre el array para buscar el id seleccionado y se eliminar
        List<Cliente> nuevos = new ArrayList<>();
        for (Cliente dato : datos) {
            if (dato.getId() != id) {
                nuevos.add(dato);
            }
        }

        try {
            //se crea el flujo de entrada y se cargar los datos al .DAT
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

        //Se llama a la funcion para actualzar la tabla
        modificarTabla();
    }
}
