import InterfazGrafica.Principal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Principal();
                frame.setSize(500, 300);
                frame.setVisible(true);
            }
        });
    }
}