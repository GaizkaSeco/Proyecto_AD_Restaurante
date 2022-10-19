package InterfazGrafica;

import Clases.Cliente;
import Clases.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class VentanaAlmacen extends JFrame{
    private JPanel panelAlmacen;
    private JTable table1;
    private JButton atrasBoton;
    private JButton anadirBoton;
    private JButton eliminarBoton;
    private JButton editarButton;
    private JButton reloadBoton;

    public VentanaAlmacen() {
        setContentPane(panelAlmacen);
        reloadBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreColumnas[] = {"id", "Producto", "Cantidad"};
                String datos[][] = new String[10][3];
                try {
                    File file = new File("Productos.dat");
                    ObjectInputStream fileobj = new ObjectInputStream(new FileInputStream(file));
                    Producto producto = (Producto) fileobj.readObject();
                    int i = 0;
                    int j = 0;
                    while (producto != null){
                        datos[i][j] = String.valueOf(producto.getId());
                        j++;
                        datos[i][j] = producto.getProducto();
                        j++;
                        datos[i][j] = String.valueOf(producto.getCantidad());
                        j = 0;
                        i++;
                        producto = null;
                    }
                    fileobj.close();
                    table1.setModel(new DefaultTableModel(datos, nombreColumnas));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ew) {
                    ew.printStackTrace();
                }
            }
        });
    }
}
