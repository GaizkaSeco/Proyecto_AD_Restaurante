package Clases;

import java.io.*;

public class CrearFichEmpleados {
    public static void main(String[] args) {
        File file = new File("Empleados.dat");
        try {
            FileOutputStream fileo = new FileOutputStream(file);
            ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

            String nombres[] = {"Juan","Javi","Leire","Gaizka","Alex","Egoitz","David","Markel","Ibai","Pepe"};
            double salarios[] = {1000, 1100, 2000, 2000, 1000, 1000, 1000, 1000, 1000, 1000};
            String fechaCon[] = {"10/10/2020","10/10/2020","10/10/2020","10/10/2020","10/10/2020","10/10/2020","10/10/2020","10/10/2020","10/10/2020","10/10/2020","10/10/2020"};
            int telefono[] = {123456789, 857412563, 968524163, 645369874, 685214796, 645893214, 628549514, 654788932, 654127398, 665244615};
            String email[] = {"juan@gmail.com","javi@gmail.com","leire@gmail.com","gaizka@gmail.com","alex@gmail.com","egoitz@gmail.com","david@gmail.com","markel@gmail.com","ibai@gmail.com","pepe@gmail.com"};

            for (int i = 0; i < nombres.length; i++) {
                Empleado empleado = new Empleado(i, nombres[i], salarios[i], fechaCon[i], telefono[i], email[i]);
                fileobj.writeObject(empleado);
            }

            fileobj.close();
            System.out.println("Se ha creado el DAT de los empleados.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
