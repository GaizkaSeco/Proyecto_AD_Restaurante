package InterfazGrafica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class VentanaEmpleados extends JFrame {
    private JPanel PanelEmpleados;
    private JTable table1;
    private JButton anadirBoton;
    private JButton eliminarBoton;
    private JButton editarBoton;
    private JButton atrasBoton;
    private JButton reloadBoton;

    public VentanaEmpleados() {
        setContentPane(PanelEmpleados);

        reloadBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreColumnas[] = {"id", "Nombre", "Salario", "Fecha Contrato", "Telefono", "Email"};
                String datos[][] = new String[0][0];
                try {
                    ObjectInputStream fileobj = new ObjectInputStream(new FileInputStream("Empleados.dat"));

                    table1.setModel(new DefaultTableModel(datos, nombreColumnas));
                } catch (IOException ex) {
                    System.out.println("ERROR al cargar los datos.");
                }
            }
        });
    }
}
