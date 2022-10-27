package InterfazGrafica;

import Clases.Empleado;
import Clases.Producto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EditarProducto extends JFrame {
    private JTextField productoField;
    private JTextField cantidadField;
    private JButton editarBoton;
    private JButton atrasBoton;
    private JPanel panelEditarProducto;
    List<Producto> datos = new ArrayList<Producto>();

    public EditarProducto(List<Producto> d, int id) {
        setContentPane(panelEditarProducto);
        for (Producto dato : datos) {
            if (dato.getId() == id) {
                productoField.setText(dato.getProducto());
                cantidadField.setText(String.valueOf(dato.getCantidad()));
            }
        }
        editarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String producto = productoField.getText();
                    int cantidad = Integer.parseInt(cantidadField.getText());

                    if (producto == null) {
                        JOptionPane.showMessageDialog(null, "Compruebe que los datos son correctos");
                    } else {
                        for (Producto dato : datos) {
                            if (dato.getId() == id) {
                                dato.setProducto(producto);
                                dato.setCantidad(cantidad);
                            }
                        }

                        File file = new File("Producto.dat");
                        FileOutputStream fileo = new FileOutputStream(file);
                        ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

                        for (Producto dato : datos) {
                            fileobj.writeObject(dato);
                        }

                        fileobj.close();

                        JOptionPane.showMessageDialog(null, "Se ha editado corectamente.");

                        JFrame frame = new VentanaAlmacen();
                        frame.setSize(500, 300);
                        frame.setVisible(true);
                        dispose();
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "No se a podido encontrar en archivo.");
                } catch (IOException ex) {
                    System.out.println("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Introduce valores correctos o comprueba el numero de telefono.");
                }
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
}
