package InterfazGrafica;

import Clases.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class VentanaClientes extends JFrame {
    private JPanel panelClientes;
    private JTable table1;
    private JButton anadirBoton;
    private JButton eliminarBoton;
    private JButton editarBoton;
    private JButton reloadBoton;
    private JButton atrasBoton;

    public VentanaClientes() {
        setContentPane(panelClientes);
        reloadBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreColumnas[] = {"id", "Nombre", "Telefono", "Email"};
                String datos[][] = new String[10][4];
                try {
                    File file = new File("Clientes.dat");
                    ObjectInputStream fileobj = new ObjectInputStream(new FileInputStream(file));
                    Cliente cliente = (Cliente) fileobj.readObject();
                    int i = 0;
                    int j = 0;
                    while (cliente != null){
                        datos[i][j] = String.valueOf(cliente.getId());
                        j++;
                        datos[i][j] = cliente.getNombre();
                        j++;
                        datos[i][j] = String.valueOf(cliente.getTelefono());
                        j++;
                        datos[i][j] = cliente.getEmail();
                        j = 0;
                        i++;
                        cliente = null;
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
