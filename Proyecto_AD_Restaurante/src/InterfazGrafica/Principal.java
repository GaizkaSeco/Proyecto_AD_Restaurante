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

    public Principal() {
        setContentPane(panel1);
        AlmacenBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new VentanaAlmacen();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        BotonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
        PlatosBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new VentanaPlatos();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        EmpleadosBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new VentanaEmpleados();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        ClientesBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new VentanaClientes();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        generarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new MenuGenerado();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
        exportBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarTodo();
            }
        });
    }

    public void exportarTodo() {
        exportEmpleados();
        exportarClientes();
        exportarProductos();
        exportarPlatos();
    }

    public void exportEmpleados() {
        ObjectInputStream fileobj = null;
        ListaEmpleados listaEmpleados = null;
        try {
            File fichero = new File("Empleados.dat");
            FileInputStream filein = new FileInputStream(fichero);
            fileobj = new ObjectInputStream(filein);
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
        } catch (IOException e) {}
        try {
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
    public void exportarClientes() {
        ObjectInputStream fileobj = null;
        ListaClientes listaClientes = null;
        try {
            File fichero = new File("Clientes.dat");
            FileInputStream filein = new FileInputStream(fichero);
            fileobj = new ObjectInputStream(filein);
            Cliente cliente;
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
        } catch (IOException e) {}
        try {
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
    public void exportarProductos() {
        ObjectInputStream fileobj = null;
        ListaProductos listaProductos = null;
        try {
            File fichero = new File("Productos.dat");
            FileInputStream filein = new FileInputStream(fichero);
            fileobj = new ObjectInputStream(filein);
            System.out.println("Comienza el proceso de creación del fichero a XML ...");
            Producto producto;
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
        } catch (IOException e) {}
        try {
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
    public void exportarPlatos() {
        ObjectInputStream fileobj = null;
        ListaPlatos listaPlatos = null;
        try {
            File fichero = new File("Platos.dat");
            FileInputStream filein = new FileInputStream(fichero);
            fileobj = new ObjectInputStream(filein);
            Plato plato;
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
        } catch (IOException e) {}
        try {
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
