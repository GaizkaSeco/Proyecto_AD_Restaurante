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
        //añadimos la lista con los datos cargados y mostramos el panel de la interfaz
        this.datos = d;
        setContentPane(panelCrearCliente);
        //Listener del boton de añadir  que llama a la funcion de añadir el cliente
        anadirBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirCliente();
            }
        });
        //Listener del boton de atras  que llama a la ventana de los clientes
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

    //Funcion de añadir al cliete
    public void anadirCliente() {
        try {
            String nombre = nombreField.getText();
            int telefono = Integer.parseInt(telefonoField.getText());
            String email = emailField.getText();

            //Se comprueba que no tengamos datos vacios
            if (nombre.trim().equals("") || email.trim().equals("") || String.valueOf(telefono).length() != 9) {
                JOptionPane.showMessageDialog(null, "Compruebe que los datos son correctos");
            } else {
                int id;
                //Se comprueba si hay datos en el array o no, ya que si el dat esta vacio podria dar problemas
                if (datos.size() == 0) {
                    id = 1;
                } else {
                    id = datos.get(datos.size() - 1).getId() + 1;
                }

                datos.add(new Cliente(id, nombre, telefono, email));

                //Se crea flujos de entrada
                File file = new File("Clientes.dat");
                FileOutputStream fileo = new FileOutputStream(file);
                ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

                //Escribimos los objetos producto
                for (Cliente dato : datos) {
                    fileobj.writeObject(dato);
                }

                //Mostramos mensage de que se ha creado correctamente y volvemos a la ventana de los clientes
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
