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
        //añadimos la lista con los datos cargados y mostramos el panel de la interfaz
        this.datos = d;
        setContentPane(panelEmpleados);
        //Listener del boton de añadir  que llama a la funcion de añadir el empleado
        anadirBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirEmpleado();
            }
        });
        //Listener del boton de atras  que llama a la ventana de los empleados
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

    //Funcion de añadir al empleado
    public void anadirEmpleado() {
        try {
            String nombre = nombreField.getText();
            Double salario = Double.parseDouble(salarioField.getText());
            String fecha = fechaField.getText();
            int telefono = Integer.parseInt(telefonoField.getText());
            String email = emailField.getText();

            //Se comprueba que no tengamos datos vacios
            if (nombre.trim().equals("") || fecha.trim().equals("") || email.trim().equals("") || String.valueOf(telefono).length() != 9) {
                JOptionPane.showMessageDialog(null, "Compruebe que los datos son correctos");
            } else {
                int id;
                //Se comprueba si hay datos en el array o no, ya que si el dat esta vacio podria dar problemas
                if (datos.size() == 0) {
                    id = 1;
                } else {
                    id = datos.get(datos.size() - 1).getId() + 1;
                }
                datos.add(new Empleado(id, nombre, salario, fecha, telefono, email));

                //Se crea flujos de entrada
                File file = new File("Empleados.dat");
                FileOutputStream fileo = new FileOutputStream(file);
                ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

                //Escribimos los objetos producto
                for (Empleado dato : datos) {
                    fileobj.writeObject(dato);
                }

                //Mostramos mensage de que se ha creado correctamente y volvemos a la venta de los empleados
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
