package Clases;

import InterfazGrafica.Login;
import InterfazGrafica.Principal;

import javax.swing.*;

public class Main {
    //Programa para iniciar la interfaz grafica
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Login();
                frame.setSize(500, 300);
                frame.setVisible(true);
            }
        });
    }
}