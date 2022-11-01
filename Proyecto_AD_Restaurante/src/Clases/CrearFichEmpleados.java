package Clases;

import java.io.*;

public class CrearFichEmpleados {
    public static void main(String[] args) {
        File file = new File("Empleados.dat");
        try {
            //Creamos los flujos de entrada
            FileOutputStream fileo = new FileOutputStream(file);
            ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

            //Creamos los arrray con los datos que queremos insertar
            String nombres[] = {"Leire", "Juan", "Javi", "Gaizka", "Alex", "Egoitz", "David", "Markel", "Ibai", "Pepe"};
            double salarios[] = {4000, 1100, 2000, 2000, 1000, 1000, 1000, 1000, 1000, 1000};
            String fechaCon[] = {"10/10/2020", "10/10/2020", "10/10/2020", "10/10/2020", "10/10/2020", "10/10/2020", "10/10/2020", "10/10/2020", "10/10/2020", "10/10/2020"};
            int telefono[] = {123456789, 857412563, 968524163, 645369874, 685214796, 645893214, 628549514, 654788932, 654127398, 665244615};
            String email[] = {"leire@gmail.com", "juan@gmail.com", "javi@gmail.com", "gaizka@gmail.com", "alex@gmail.com", "egoitz@gmail.com", "david@gmail.com", "markel@gmail.com", "ibai@gmail.com", "pepe@gmail.com"};

            //Insertamos los datos creando el objeto
            for (int i = 0; i < nombres.length; i++) {
                Empleado empleado = new Empleado(i + 1, nombres[i], salarios[i], fechaCon[i], telefono[i], email[i]);
                fileobj.writeObject(empleado);
            }

            fileobj.close();
            System.out.println("Se ha creado el DAT de los empleados.");

            //Creamos los flujos de salida
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobjin = new ObjectInputStream(filein);

            Empleado empleado;
            //Mostramos los datos para saber si se ha generado bien el archivo
            while (((empleado = (Empleado) fileobjin.readObject()) != null)) {
                System.out.println(empleado.nombre);
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
