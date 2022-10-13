package InterfazGrafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame {
    private JButton ReservasBoton;
    private JButton PlatosBoton;
    private JButton AlmacenBoton;
    private JButton ClientesBoton;
    private JPanel panel1;
    private JButton EmpleadosBoton;
    private JButton BotonSalir;

    public Principal() {
        setContentPane(panel1);
        AlmacenBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new VentanaAlmacen();
                frame.setSize(500, 300);
                frame.setVisible(true);
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
            }
        });
    }
}
