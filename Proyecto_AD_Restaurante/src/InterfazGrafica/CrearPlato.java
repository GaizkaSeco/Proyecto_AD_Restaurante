package InterfazGrafica;

import Clases.Empleado;
import Clases.Plato;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CrearPlato extends JFrame {
    private JPanel panelAnadirPlato;
    private JTextField platoField;
    private JTextField descripcionField;
    private JTextField costeField;
    private JComboBox comboBox1;
    private JButton anadirBoton;
    private JButton atrasBoton;
    public List<Plato> datos = new ArrayList<Plato>();

    public CrearPlato(List<Plato> d) {
        this.datos = d;
        comboBox1.addItem("Primero");
        comboBox1.addItem("Segundo");
        comboBox1.addItem("Tercero");
        setContentPane(panelAnadirPlato);
        anadirBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirPlato();
            }
        });
        atrasBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new VentanaPlatos();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
    }

    public void anadirPlato() {
        try {
            String plato = platoField.getText();
            String descripcion = descripcionField.getText();
            int precio = Integer.parseInt(costeField.getText());
            int categoria = comboBox1.getSelectedIndex() + 1;
            int id = datos.get(datos.size() - 1).getId() + 1;

            if (plato == null || descripcion == null) {
                JOptionPane.showMessageDialog(null, "Compruebe que los datos son correctos");
            } else {
                datos.add(new Plato(id, plato, descripcion, precio, categoria));

                File file = new File("Platos.dat");
                FileOutputStream fileo = new FileOutputStream(file);
                ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

                for (Plato dato : datos) {
                    fileobj.writeObject(dato);
                }

                JOptionPane.showMessageDialog(null, "El plato se ha añadido corectamente.");

                fileobj.close();
                JFrame frame = new VentanaPlatos();
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
