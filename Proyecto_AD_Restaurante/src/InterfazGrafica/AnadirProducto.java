package InterfazGrafica;

import Clases.Cliente;
import Clases.Empleado;
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
        this.datos = d;
        setContentPane(panelAnadirProducto);
        anadirBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirProducto();
            }
        });
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

    public void anadirProducto() {
        try {
            String producto = producotField.getText();
            int cantidad = Integer.parseInt(cantidadField.getText());

            if (producto.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Compruebe que los datos son correctos");
            }else {
                int id = datos.get(datos.size() - 1).getId() + 1;

                datos.add(new Producto(id, producto, cantidad));

                File file = new File("Productos.dat");
                FileOutputStream fileo = new FileOutputStream(file);
                ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

                for (Producto dato : datos) {
                    fileobj.writeObject(dato);
                }

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
