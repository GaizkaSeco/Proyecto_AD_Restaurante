package Clases;

import java.io.*;

public class CrearFichPlatos {
    public static void main(String[] args) {
        File file = new File("Platos.dat");
        try {
            FileOutputStream fileo = new FileOutputStream(file);
            ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

            String platos[] = {"Macarrones con tomate", "Pechugas de pollo","Lentejas","Ensalada", "Helado de chocolate", "Tarta de chocolate"};
            String descripcion[] = {"Ricos macarrones con tomate caseros", "Pechuga de pollo rebozada", "Lentejas caseras", "Ensalada con tomate, lechuga y lo que quieras", "Helado de chocolate casero", "Tarta de queso casera"};
            double precio[] = {4.95, 4.95, 7.5, 4.95, 4.99, 9.99};
            int categoria[] = {1, 2, 2, 1, 3, 3};

            for (int i = 0; i < platos.length; i++) {
                Plato plato = new Plato(i + 1, platos[i], descripcion[i], precio[i], categoria[i]);
                fileobj.writeObject(plato);
            }

            fileobj.close();
            System.out.println("Se ha creado el DAT de los platos.");

            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobjin = new ObjectInputStream(filein);

            Plato plato;

            while (((plato = (Plato) fileobjin.readObject()) != null)) {
                System.out.println(plato.nombre);
            }

            fileobjin.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
        } catch (IOException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            System.out.println("No se ha encontrado la clase");
        }
    }
}
