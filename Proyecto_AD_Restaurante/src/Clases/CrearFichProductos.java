package Clases;

import java.io.*;

public class CrearFichProductos {
    public static void main(String[] args) {
        File file = new File("Productos.dat");
        try {
            //Creamos los flujos de entrada
            FileOutputStream fileo = new FileOutputStream(file);
            ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

            //Creamos los arrray con los datos que queremos insertar
            String productos[] = {"Tomate","Lechuga","Carne de vaca","Pez blanco","Huevos","Harina","Centollo","Zanahoria","Patatas","Aceite"};
            int cantidad[] = {100, 20, 50, 50, 150, 100, 50, 100, 100, 250};

            //Insertamos los datos creando el objeto
            for (int i = 0; i < productos.length; i++) {
                Producto producto = new Producto(i, productos[i], cantidad[i]);
                fileobj.writeObject(producto);
            }

            fileobj.close();
            System.out.println("Se ha creado el DAT de los Productos.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
