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


    public CrearEmpleado() {
        setContentPane(panelEmpleados);
        cargarDatos();
        anadirBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
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

    public void cargarDatos() {
        try {
            File file = new File("Empleados.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            datos.clear();
            Empleado empleado;
            while ((empleado = (Empleado) fileobj.readObject()) != null) {
                datos.add(empleado);
            }
            fileobj.close();
        } catch (IOException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Ha surgido un error al intentar acceder al los datos.");
        }
    }

    public void anadirEmpleado() {
        try {
            String nombre = nombreField.getText();
            Double salario = Double.parseDouble(salarioField.getText());
            System.out.println("salario validado");
            String fecha = fechaField.getText();
            int telefono = Integer.parseInt(telefonoField.getText());
            String email = emailField.getText();

            int id = datos.get(datos.size() - 1).getId() + 1;

            datos.add(new Empleado(id,  nombre, salario, fecha, telefono, email));

            File file = new File("Empleados.dat");
            FileOutputStream fileo = new FileOutputStream(file);
            ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

            for (Empleado dato : datos) {
                fileobj.writeObject(dato);
            }
            System.out.println("Lo ha añadido");

            JFrame frame = new VentanaEmpleados();
            frame.setSize(500, 300);
            frame.setVisible(true);
            dispose();

            fileobj.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Porfavor introduce numeros y no letras.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se a podido encontrar en archivo.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
