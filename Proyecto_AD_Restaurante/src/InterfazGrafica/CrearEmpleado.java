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
                //boton atras todavia no hecho.
            }
        });
    }

    public void cargarDatos() {
        try {
            datos.clear();
            File file = new File("Empleados.dat");
            ObjectInputStream fileobj = new ObjectInputStream(new FileInputStream(file));
            Empleado empleado = (Empleado) fileobj.readObject();
            while (empleado != null) {
                datos.add(empleado);
                empleado = null;
            }
            fileobj.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
