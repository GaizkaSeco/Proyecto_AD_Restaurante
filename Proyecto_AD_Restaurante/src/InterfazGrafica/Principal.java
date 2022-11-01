package InterfazGrafica;

import Clases.*;
import com.thoughtworks.xstream.XStream;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Principal extends JFrame {
    private JButton generarBoton;
    private JButton PlatosBoton;
    private JButton AlmacenBoton;
    private JButton ClientesBoton;
    private JPanel panel1;
    private JButton EmpleadosBoton;
    private JButton BotonSalir;
    private JButton exportBoton;

    //Ventana principal donde estaran los botones para ir a cada apartado de la aplicacion
    public Principal() {
        //Mostramos el panel de la ventana principal
        setContentPane(panel1);
        //Listener del boton de la ventana de productos donde se pueden gestionar todos los productos
        AlmacenBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new VentanaAlmacen();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        //Listener del boton de salir el cual termina la aplicacion
        BotonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
        //Listener del boton de la ventana de platos donde se pueden gestionar todos los platos
        PlatosBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new VentanaPlatos();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        //Listener del boton de la ventana de empleados donde se pueden gestionar todos los empleados
        EmpleadosBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new VentanaEmpleados();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        //Listener del boton de la ventana de clientes donde se pueden gestionar todos los clientes
        ClientesBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new VentanaClientes();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        //Listener del boton de la ventana de generar un menu aleatorio donde se pueden ver un menu generado aleatoriomenre y se puede exportar a XML
        generarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new MenuGenerado();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        //Listener del boton que exporta todos los .DAT de la aplicacion a XML con XStream, llama a la funcion expotarTodo
        exportBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarTodo();
            }
        });
    }

    //Funcion para exportar uno a uno cada .DAT de la aplicacion a XML
    public void exportarTodo() {
        exportEmpleados();
        exportarClientes();
        exportarProductos();
        exportarPlatos();
    }

    //Exporta el .DAT de los empleados a XML
    public void exportEmpleados() {
        ObjectInputStream fileobj = null;
        ListaEmpleados listaEmpleados = null;
        try {
            //Se crea el flujo de salida
            File fichero = new File("Empleados.dat");
            FileInputStream filein = new FileInputStream(fichero);
            fileobj = new ObjectInputStream(filein);
            //Se leen todos los datos almacenados y se añaden a la clase listaEmpleados
            Empleado empleado;
            listaEmpleados = new ListaEmpleados();
            while ((empleado = (Empleado) fileobj.readObject()) != null) {
                listaEmpleados.add(empleado);
            }
        } catch (EOFException e) {

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado.");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar la clase.");
        }
        try {
            fileobj.close();
        } catch (IOException e) {
        }
        try {
            //Se usa XStream para exportar los datos
            XStream xstream = new XStream();
            xstream.alias("ListaEmpleados", ListaEmpleados.class);
            xstream.alias("DatosEmpleado", Empleado.class);
            xstream.addImplicitCollection(ListaEmpleados.class, "lista");
            xstream.toXML(listaEmpleados, new FileOutputStream("EmpleadosXML.xml"));
            JOptionPane.showMessageDialog(null, "El fichero de empleados XML se ha creado correctamente.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero.");
        }
    }

    //Exporta el .DAT de los clienets a XML
    public void exportarClientes() {
        ObjectInputStream fileobj = null;
        ListaClientes listaClientes = null;
        try {
            //Se crea el flujo de salida
            File fichero = new File("Clientes.dat");
            FileInputStream filein = new FileInputStream(fichero);
            fileobj = new ObjectInputStream(filein);
            Cliente cliente;
            //Se leen todos los datos almacenados y se añaden a la clase listaClientes
            listaClientes = new ListaClientes();
            while ((cliente = (Cliente) fileobj.readObject()) != null) {
                listaClientes.add(cliente);
            }
        } catch (EOFException e) {

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado.");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar la clase.");
        }
        try {
            fileobj.close();
        } catch (IOException e) {
        }
        try {
            //Se usa XStream para exportar los datos
            XStream xstream = new XStream();
            xstream.alias("ListaClientes", ListaClientes.class);
            xstream.alias("DatosCliente", Cliente.class);
            xstream.addImplicitCollection(ListaClientes.class, "lista");
            xstream.toXML(listaClientes, new FileOutputStream("ClientesXML.xml"));
            JOptionPane.showMessageDialog(null, "El fichero de clientes XML se ha creado correctamente.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero.");
        }
    }

    //Exporta el .DAT de los productos a XML
    public void exportarProductos() {
        ObjectInputStream fileobj = null;
        ListaProductos listaProductos = null;
        try {
            //Se crea el flujo de salida
            File fichero = new File("Productos.dat");
            FileInputStream filein = new FileInputStream(fichero);
            fileobj = new ObjectInputStream(filein);
            System.out.println("Comienza el proceso de creación del fichero a XML ...");
            Producto producto;
            //Se leen todos los datos almacenados y se añaden a la clase listaProductos
            listaProductos = new ListaProductos();
            while ((producto = (Producto) fileobj.readObject()) != null) {
                listaProductos.add(producto);
            }
        } catch (EOFException e) {

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado.");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar la clase.");
        }
        try {
            fileobj.close();
        } catch (IOException e) {
        }
        try {
            //Se usa XStream para exportar los datos
            XStream xstream = new XStream();
            xstream.alias("ListaProductos", ListaProductos.class);
            xstream.alias("DatosProducto", Producto.class);
            xstream.addImplicitCollection(ListaProductos.class, "lista");
            xstream.toXML(listaProductos, new FileOutputStream("ProductosXML.xml"));
            JOptionPane.showMessageDialog(null, "El fichero de productos XML se ha creado correctamente.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero.");
        }
    }

    //Exporta el .DAT de los platos a XML
    public void exportarPlatos() {
        ObjectInputStream fileobj = null;
        ListaPlatos listaPlatos = null;
        try {
            //Se crea el flujo de salida
            File fichero = new File("Platos.dat");
            FileInputStream filein = new FileInputStream(fichero);
            fileobj = new ObjectInputStream(filein);
            Plato plato;
            //Se leen todos los datos almacenados y se añaden a la clase listaPlatos
            listaPlatos = new ListaPlatos();
            while ((plato = (Plato) fileobj.readObject()) != null) {
                listaPlatos.add(plato);
            }
        } catch (EOFException e) {

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado.");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar la clase.");
        }
        try {
            fileobj.close();
        } catch (IOException e) {
        }
        try {
            //Se usa XStream para exportar los datos
            XStream xstream = new XStream();
            xstream.alias("ListaPlatos", ListaPlatos.class);
            xstream.alias("DatosPlato", Plato.class);
            xstream.addImplicitCollection(ListaPlatos.class, "lista");
            xstream.toXML(listaPlatos, new FileOutputStream("PlatosXML.xml"));
            JOptionPane.showMessageDialog(null, "El fichero de platos XML se ha creado correctamente.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero.");
        }
    }
}
