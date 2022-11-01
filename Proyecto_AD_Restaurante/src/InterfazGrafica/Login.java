package InterfazGrafica;

import Clases.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Login extends JFrame {
    private JPanel loginPanel;
    private JTextField contrasenaField;
    private JTextField usuarioField;
    private JButton inicarSesionBoton;

    public Login() {
        //Mostramos el panel de la interfaz
        setContentPane(loginPanel);
        //Listener del boton de iniciar sesion que se encarga de comprobar si es correcta la informacion
        inicarSesionBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Se crea el flujo de salida
                ObjectInputStream fileobj = null;
                boolean enter = false;
                try {
                    File file = new File("Login.dat");
                    FileInputStream fileo = new FileInputStream(file);
                    fileobj = new ObjectInputStream(fileo);

                    //se busca si existe algun usuario con el cual coincidan los datos introducidos
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
                //Si se han acertado el nombre de usuario y contrasena se le envia a la ventana pricipal
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
