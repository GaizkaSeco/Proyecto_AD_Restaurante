package InterfazGrafica;

import Clases.Plato;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenuGenerado extends JFrame {
    private JPanel panelGenerado;
    private JTextField primeroField;
    private JTextField segundoField;
    private JTextField postreField;
    private JButton guardarBoton;
    private JButton atrasBoton;
    private List<Plato> menu = new ArrayList<Plato>();
    private List<Plato> datos = new ArrayList<Plato>();

    public MenuGenerado() {
        //Visualizamos el panel de la interfaz grafica
        setContentPane(panelGenerado);
        generarMenu();
        //Listener del boton de guardar el menu
        guardarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Export los datos del menu generado a XML con DOM
                try {
                    //creamos el documento con DOM
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    DOMImplementation implementation = builder.getDOMImplementation();
                    Document document = implementation.createDocument(null, "MenuAleatorio", null);
                    document.setXmlVersion("1.0");

                    //Creamos cada elemento con la funcion de crearElemento
                    for (int i = 0; i < menu.size(); i++) {
                        Element raiz = document.createElement("plato");
                        document.getDocumentElement().appendChild(raiz);
                        crearElemento("id", String.valueOf(menu.get(i).getId()), raiz, document);
                        crearElemento("plato", menu.get(i).getNombre(), raiz, document);
                        crearElemento("descripcion", menu.get(i).getDescripcion(), raiz, document);
                        crearElemento("categoria", String.valueOf(menu.get(i).getCategoria()), raiz, document);
                    }

                    //Creamos el documento XML con la informacion que tenemos
                    Source source = new DOMSource(document);
                    Result result = new StreamResult(new java.io.File("MenuAleatorio.xml"));
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.transform(source, result);
                    Result console = new StreamResult(System.out);
                    transformer.transform(source, console);

                    JOptionPane.showMessageDialog(null, "Se ha creado el XML con en menu.");
                } catch (ParserConfigurationException ex) {
                    JOptionPane.showMessageDialog(null, "ERROR al generar el XML del menu.");
                } catch (TransformerConfigurationException ex) {
                    JOptionPane.showMessageDialog(null, "ERROR al generar el XML del menu.");
                } catch (TransformerException ex) {
                    JOptionPane.showMessageDialog(null, "ERROR al generar el XML del menu.");
                }
            }
        });
        //Listener del boton de atras con el que se vuelve a la venatana principal
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

    //Funcion que genera un menu aleatorio con todos los platos que hay en el restautante
    public void generarMenu() {
        //Seleccionamos los patos por su categoia si es primero, segundo o postre
        Random r = new Random();
        cargarDatos();
        if (datos.size() != 0) {
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

            //Elegimos uno de cada categoria
            int p = primeros.size();
            int s = segundos.size();
            int po = postres.size();
            //control por si no hay primeros generados
            if (primeros.size() == 0) {
                menu.add(new Plato(""));
            } else if (p == 1) {
                menu.add(primeros.get(0));
            } else {
                menu.add(primeros.get(r.nextInt(0, p)));
            }
            //control por si no hay segundos generados
            if (segundos.size() == 0) {
                menu.add(new Plato(""));
            } else if (s == 1) {
                menu.add(segundos.get(0));
            } else {
                menu.add(segundos.get(r.nextInt(0, s)));
            }
            //control por si no hay postres generados
            if (postres.size() == 0) {
                menu.add(new Plato(""));
            } else if (po == 1) {
                menu.add(postres.get(0));
            } else {
                menu.add(postres.get(r.nextInt(0, po)));
            }

            //Los mostramos en los TextField
            primeroField.setText(menu.get(0).getNombre());
            segundoField.setText(menu.get(1).getNombre());
            postreField.setText(menu.get(2).getNombre());
        } else {
            JOptionPane.showMessageDialog(null, "No hay platos creados");
        }
    }

    //funcion para cargar en un array todos los objetos almacenados en el .DAT
    public void cargarDatos() {
        try {
            //creamos el flujo de salida
            File file = new File("Platos.dat");
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobj = new ObjectInputStream(filein);

            //Limpiamos el array por si acaso
            datos.clear();
            //añadimos los objetos que hay en el .DAT al array uno a uno
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

    //Funcion para crear un elemento
    static void crearElemento(String datoPlato, String valor, Element raiz, Document document) {
        Element elem = document.createElement(datoPlato);
        Text text = document.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(text);
    }
}
