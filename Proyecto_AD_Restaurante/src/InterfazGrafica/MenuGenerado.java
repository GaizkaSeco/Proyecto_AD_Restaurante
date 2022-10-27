package InterfazGrafica;

import Clases.Plato;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenuGenerado extends JFrame{
    private JPanel panelGenerado;
    private JTextField primeroField;
    private JTextField segundoField;
    private JTextField postreField;
    private JButton guardarBoton;
    private JButton atrasBoton;
    private List<Plato> menu = new ArrayList<Plato>();
    private List<Plato> datos = new ArrayList<Plato>();

    public MenuGenerado() {
        setContentPane(panelGenerado);
        generarMenu();
        guardarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        atrasBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Principal();
                frame.setSize(500, 300);
                frame.setVisible(true);
                dispose();
            }
        });
    }

    public void generarMenu() {
        Random r = new Random();
        cargarDatos();
        List<Plato> primeros = new ArrayList<Plato>();
        List<Plato> segundos = new ArrayList<Plato>();
        List<Plato> postres = new ArrayList<Plato>();
        for (Plato dato : datos) {
            if (dato.getCategoria() == 1) {
                primeros.add(dato);
            } else if (dato.getCategoria() == 2) {
                segundos.add(dato);
            } else if (dato.getCategoria() == 3) {
                postres.add(dato);
            }
        }

        int p = primeros.size();
        int s = segundos.size();
        int po = postres.size();
        if (p == 1) {
            p = 1;
        } else if (s == 1) {
            s = 1;
        } else if (po == 1) {
            po = 1;
        }

        menu.add(primeros.get(r.nextInt(0, p)));
        menu.add(segundos.get(r.nextInt(0, s)));
        menu.add(postres.get(r.nextInt(0, po)));

        primeroField.setText(menu.get(0).getNombre());
        segundoField.setText(menu.get(1).getNombre());
        postreField.setText(menu.get(2).getNombre());
    }

    public void cargarDatos() {
        try {
            File file = new File("Platos.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            datos.clear();
            Plato plato;
            while ((plato = (Plato) fileobj.readObject()) != null) {
                datos.add(plato);
            }
            fileobj.close();
        } catch (IOException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Ha surgido un error al intentar acceder al los datos.");
        }
    }
}
