package InterfazGrafica;

import Clases.Empleado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CrearEmpleado extends JFrame {
    private JTextField nombreField;
    private JTextField salarioField;
    private JTextField emailField;
    private JPanel panelEmpleados;
    private JButton anadirBoton;
    private JButton atrasButon;
    private JTextField telefonoField;
    private JTextField fechaField;
    public List<Empleado> datos = new ArrayList<Empleado>();


    public CrearEmpleado(List<Empleado> d) {
        this.datos = d;
        setContentPane(panelEmpleados);
        anadirBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirEmpleado();
            }
        });
        atrasButon.addActionListener(new ActionListener() {
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
            Double salario = Double.parseDouble(salarioField.getText());
            String fecha = fechaField.getText();
            int telefono = Integer.parseInt(telefonoField.getText());
            String email = emailField.getText();

            int id = datos.get(datos.size() - 1).getId() + 1;

            if (nombre == null || fecha == null || email == null || String.valueOf(telefono).length() != 9) {
                JOptionPane.showMessageDialog(null, "Compruebe que los datos son correctos");
            } else {
                datos.add(new Empleado(id, nombre, salario, fecha, telefono, email));

                File file = new File("Empleados.dat");
                FileOutputStream fileo = new FileOutputStream(file);
                ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

                for (Empleado dato : datos) {
                    fileobj.writeObject(dato);
                }

                JOptionPane.showMessageDialog(null, "El empleado se ha añadido corectamente.");

                fileobj.close();

                JFrame frame = new VentanaEmpleados();
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
