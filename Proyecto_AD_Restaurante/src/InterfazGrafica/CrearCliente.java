package InterfazGrafica;

import Clases.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CrearCliente extends JFrame {
    private JPanel panelCrearCliente;
    private JTextField nombreField;
    private JTextField telefonoField;
    private JTextField emailField;
    private JButton anadirBoton;
    private JButton atrasBoton;
    List<Cliente> datos = new ArrayList<Cliente>();

    public CrearCliente(List<Cliente> d) {
        this.datos = d;
        setContentPane(panelCrearCliente);
        anadirBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirEmpleado();
            }
        });
        atrasBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new VentanaEmpleados();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
    }

    public void anadirEmpleado() {
        try {
            String nombre = nombreField.getText();
            int telefono = Integer.parseInt(telefonoField.getText());
            String email = emailField.getText();

            if (nombre == null || email == null || String.valueOf(telefono).length() != 9) {
                JOptionPane.showMessageDialog(null, "Compruebe que los datos son correctos");
            } else {
                int id = datos.get(datos.size() - 1).getId() + 1;

                datos.add(new Cliente(id, nombre, telefono, email));

                File file = new File("Clientes.dat");
                FileOutputStream fileo = new FileOutputStream(file);
                ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

                for (Cliente dato : datos) {
                    fileobj.writeObject(dato);
                }

                JOptionPane.showMessageDialog(null, "El cliente se ha añadido corectamente.");

                fileobj.close();
                JFrame frame = new VentanaClientes();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Introduce valores correctos o comprueba el numero de telefono.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se a podido encontrar en archivo.");
        } catch (IOException e) {
            System.out.println("");
        }
    }
}
