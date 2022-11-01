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
        //llamamos a la funcion para cargar los datos en la tabla
        modificarTabla();
        //Mostramos el panel de la interfaz grafica
        setContentPane(PanelPlatos);
        //Listener del boton de añadir platos que carga la ventana de añadir plato
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
        //Listener del boton de aliminar el cual le manda a una funcion el id del elemento seleccionado en la tabla el cual se quiere eliminar
        eliminarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Para eliminar debes seleccionar en la tabla.");
                } else {
                    int id = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 0).toString());
                    eliminarPlato(id);
                }
            }
        });
        //Listener del boton de atras el cual vuelve a la ventana principal
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
            File file = new File("Platos.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            //cargamos los datos al array
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
        //Nombre de las columnas y cargamos los datos al array que se le van a enviar al la tabla para cargar los datos
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
        //se carga el modelo de la tabla
        table1.setModel(new DefaultTableModel(d, nombreColumnas));
    }

    public void eliminarPlato(int id) {
        cargarDatos();
        //Se recorre el array para buscar el id seleccionado y se eliminar
        List<Plato> nuevos = new ArrayList<>();
        for (Plato dato : datos) {
            if (dato.getId() != id) {
                //preguntar si tiene sentido que no al eliminar no se cambien los id o es mejor que no alla ids vacios
                nuevos.add(dato);
            }
        }

        try {
            //se crea el flujo de entrada y se cargar los datos al .DAT
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

        //Se llama a la funcion para actualzar la tabla
        modificarTabla();
    }
}
