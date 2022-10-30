package InterfazGrafica;

import Clases.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Login extends JFrame{
    private JPanel loginPanel;
    private JTextField contrasenaField;
    private JTextField usuarioField;
    private JButton inicarSesionBoton;

    public Login() {
        setContentPane(loginPanel);
        inicarSesionBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ObjectInputStream fileobj = null;
                boolean enter = false;
                try {
                    File file = new File("Login.dat");
                    FileInputStream fileo = new FileInputStream(file);
                    fileobj = new ObjectInputStream(fileo);

                    Usuario usuario;
                    while ((usuario = (Usuario) fileobj.readObject()) != null) {
                        if (usuario.getUsuario().equals(usuarioField.getText()) && usuario.getContrasena().equals(contrasenaField.getText())) {
                            enter = true;
                        }
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo de login.");
                } catch (IOException ex) {
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado la clase necesaria.");
                }
                if (enter) {
                    JFrame frame = new Principal();
                    frame.setSize(500, 300);
                    frame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta.");
                }
                try {
                    fileobj.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado.");
                }
            }
        });
    }
}
