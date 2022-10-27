package InterfazGrafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGenerado extends JFrame{
    private JPanel panelGenerado;
    private JTextField primeroField;
    private JTextField segundoField;
    private JTextField postreField;
    private JButton guardarBoton;
    private JButton atrasBoton;

    public MenuGenerado() {
        setContentPane(panelGenerado);
        guardarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        atrasBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
