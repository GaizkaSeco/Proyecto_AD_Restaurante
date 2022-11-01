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
        //añadimos la lista con los datos cargados y mostramos el panel de la interfaz
        this.datos = d;
        //Añadimos las opciones que queremos al combobox
        comboBox1.addItem("Primero");
        comboBox1.addItem("Segundo");
        comboBox1.addItem("Tercero");
        setContentPane(panelAnadirPlato);
        //Listener del boton de añadir  que llama a la funcion de añadir el plato
        anadirBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirPlato();
            }
        });
        //Listener del boton de atras  que llama a la ventana de los clientes
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

    //Funcion de añadir al plato
    public void anadirPlato() {
        try {
            String plato = platoField.getText();
            String descripcion = descripcionField.getText();
            int precio = Integer.parseInt(costeField.getText());
            int categoria = comboBox1.getSelectedIndex() + 1;

            //Se comprueba que no tengamos datos vacios
            if (plato.trim().equals("") || descripcion.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Compruebe que los datos son correctos");
            } else {
                int id;
                //Se comprueba si hay datos en el array o no, ya que si el dat esta vacio podria dar problemas
                if (datos.size() == 0) {
                    id = 1;
                } else {
                    id = datos.get(datos.size() - 1).getId() + 1;
                }
                datos.add(new Plato(id, plato, descripcion, precio, categoria));

                //Se crea flujos de entrada
                File file = new File("Platos.dat");
                FileOutputStream fileo = new FileOutputStream(file);
                ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

                //Escribimos los objetos producto
                for (Plato dato : datos) {
                    fileobj.writeObject(dato);
                }

                //Mostramos mensage de que se ha creado correctamente y volvemos a la ventana de los platos
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
