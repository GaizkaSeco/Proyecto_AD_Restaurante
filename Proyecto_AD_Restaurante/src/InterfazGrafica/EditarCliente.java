package InterfazGrafica;

import Clases.Cliente;
import Clases.Empleado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class EditarCliente extends JFrame {
    private JTextField nombreField;
    private JTextField telefonoField;
    private JTextField emailField;
    private JButton editarBoton;
    private JButton atrasBoton;
    private JPanel panelEditarClientes;

    public EditarCliente(int id, List<Cliente> datos) {
        setContentPane(panelEditarClientes);
        for (Cliente dato : datos) {
            if (dato.getId() == id) {
                nombreField.setText(dato.getNombre());
                telefonoField.setText(String.valueOf(dato.getTelefono()));
                emailField.setText(dato.getEmail());
            }
        }
        editarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = nombreField.getText();
                    int telefono = Integer.parseInt(telefonoField.getText());
                    String email = emailField.getText();

                    if (nombre == null || email == null || String.valueOf(telefono).length() == 9) {
                        JOptionPane.showMessageDialog(null, "Compruebe que los datos son correctos");
                    } else {
                        for (Cliente dato : datos) {
                            if (dato.getId() == id) {
                                dato.setNombre(nombre);
                                dato.setTelefono(telefono);
                                dato.setEmail(email);
                            }
                        }

                        File file = new File("Clientes.dat");
                        FileOutputStream fileo = new FileOutputStream(file);
                        ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

                        for (Cliente dato : datos) {
                            fileobj.writeObject(dato);
                        }

                        fileobj.close();

                        JOptionPane.showMessageDialog(null, "Se ha editado corectamente.");

                        JFrame frame = new VentanaClientes();
                        frame.setSize(500, 300);
                        frame.setVisible(true);
                        dispose();
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "No se ha podido encontrar en archivo.");
                } catch (IOException ex) {
                    System.out.println("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Introduce valores correctos o comprueba el numero de telefono.");
                }
            }
        });
        atrasBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new VentanaClientes();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
    }
}
