package InterfazGrafica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import Clases.*;

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
                String datos[][] = new String[10][6];
                try {
                    File file = new File("Empleados.dat");
                    ObjectInputStream fileobj = new ObjectInputStream(new FileInputStream(file));
                    Empleado empleado = (Empleado) fileobj.readObject();
                    int i = 0;
                    int j = 0;
                    while (empleado != null){
                        datos[i][j] = String.valueOf(empleado.getId());
                        j++;
                        datos[i][j] = empleado.getNombre();
                        j++;
                        datos[i][j] = empleado.getSalario().toString();
                        j++;
                        datos[i][j] = empleado.getFechaCon();
                        j++;
                        datos[i][j] = String.valueOf(empleado.getTelefono());
                        j++;
                        datos[i][j] = empleado.getEmail();
                        j = 0;
                        i++;
                        empleado = null;
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
