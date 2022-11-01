package InterfazGrafica;

import Clases.Producto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnadirProducto extends JFrame {
    private JTextField producotField;
    private JTextField cantidadField;
    private JButton anadirBoton;
    private JButton atrasBoton;
    private JPanel panelAnadirProducto;
    List<Producto> datos = new ArrayList<Producto>();

    public AnadirProducto(List<Producto> d) {
        //añadimos la lista con los datos cargados y mostramos el panel de la interfaz
        this.datos = d;
        setContentPane(panelAnadirProducto);
        //Listener del boton de añadir  que llama a la funcion de añadir el producto
        anadirBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirProducto();
            }
        });
        //Listener del boton de atras que llama a la ventana de almacen
        atrasBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new VentanaAlmacen();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
    }

    //Funcion de añadir el producto
    public void anadirProducto() {
        try {
            String producto = producotField.getText();
            int cantidad = Integer.parseInt(cantidadField.getText());

            //Se comprueba que no tengamos datos vacios
            if (producto.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Compruebe que los datos son correctos");
            }else {
                int id;
                //Se comprueba si hay datos en el array o no, ya que si el dat esta vacio podria dar problemas
                if (datos.size() == 0) {
                    id = 1;
                } else {
                    id = datos.get(datos.size() - 1).getId() + 1;
                }
                datos.add(new Producto(id, producto, cantidad));

                //Se crea flujos de entrada
                File file = new File("Productos.dat");
                FileOutputStream fileo = new FileOutputStream(file);
                ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

                //Escribimos los objetos producto
                for (Producto dato : datos) {
                    fileobj.writeObject(dato);
                }

                //Mostramos mensage de que se ha creado correctamente y volvemos a la venta de almacen
                JOptionPane.showMessageDialog(null, "El producto se ha añadido corectamente.");

                fileobj.close();
                JFrame frame = new VentanaAlmacen();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Introduce valores correctos o comprueba el numero de telefono.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se a podido encontrar en archivo.");
        } catch (IOException e) {
            System.out.println("");
        }
    }
}
