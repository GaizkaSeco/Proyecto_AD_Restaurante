package Clases;

import java.io.*;

public class CrearFichClientes {
    public static void main(String[] args) {
        File file = new File("Clientes.dat");
        try {
            FileOutputStream fileo = new FileOutputStream(file);
            ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

            String nombres[] = {"Juan","Jose","Mario","Maria","Aitor","Hector","Laura","Cristian","David","Maite"};
            int telefono[] = {526987431, 659478213, 654987321, 632154879, 614239875, 664335980, 605897422, 653020489, 632101548, 678954123};
            String email[] = {"juan@gmail.com","jose@gmail.com","mario@gmail.com","maria@gmail.com","aitor@gmail.com","hector@gmail.com","laura@gmail.com","cristian@gmail.com","david@gmail.com","maite@gmail.com"};

            for (int i = 0; i < nombres.length; i++) {
                Cliente cliente = new Cliente(i, nombres[i], telefono[i], email[i]);
                fileobj.writeObject(cliente);
            }

            fileobj.close();
            System.out.println("Se ha creado el DAT de los Clientes.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
