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
        //Mostramos el panel de la interfaz grafica
        setContentPane(panelAlmacen);
        //lamamos a la funcion para cargar los datos en la tabla
        modificarTabla();
        //Listener del boton de volver a cargar los datos por si es necesatio volver a actulalizar la tabla
        reloadBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarTabla();
            }
        });
        //Listener del boton de añadir producto que carga la venata de añadir producto
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
        //Listener del boton de aliminar el cual le manda a una funcion el id del elemento seleccionado en tabla el la cual se quiere eliminar
        eliminarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Para eliminar debes seleccionar en la tabla.");
                } else {
                    //Obtencion del id del objeto seleccionaod en la tabla
                    int id = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 0).toString());
                    eliminarProducto(id);
                }
            }
        });
        //Listener del boton de de editar el cual manda el id y los datos cargados a la ventana de editar
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
            File file = new File("Productos.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            //cragamos los datos al array
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
        //Nombre de las columnas y cargamos los datos al array que se le van a enviar al la tabla para cargar los datos
        String[] nombreColumnas = {"id", "Nombre", "Cantidad"};
        int cantidad = datos.size();
        String[][] d = new String[cantidad][3];
        for (int i = 0; i < datos.size(); i++) {
            d[i][0] = String.valueOf(datos.get(i).getId());
            d[i][1] = String.valueOf(datos.get(i).getProducto());
            d[i][2] = String.valueOf(datos.get(i).getCantidad());
        }
        //se carga el modelo de la tabla
        table1.setModel(new DefaultTableModel(d, nombreColumnas));
    }

    //Funcion para eliminar el producto seleccionado
    public void eliminarProducto(int id) {
        cargarDatos();
        //Se recorre el array para buscar el id seleccionado y se eliminar
        List<Producto> nuevos = new ArrayList<>();
        for (Producto dato : datos) {
            if (dato.getId() != id) {
                nuevos.add(dato);
            }
        }

        try {
            //se crea el flujo de entrada y se cargar los datos al .DAT
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

        //Se llama a la funcion para actualzar la tabla
        modificarTabla();
    }
}
