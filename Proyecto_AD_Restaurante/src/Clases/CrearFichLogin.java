package Clases;

import java.io.*;

public class CrearFichLogin {
    public static void main(String[] args) {
        File file = new File("Login.dat");
        try {
            //Creamos los flujos de entrada
            FileOutputStream fileo = new FileOutputStream(file);
            ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

            //Creamos los arrray con los datos que queremos insertar
            String usuarios[] = {"admin"};
            String contrasena[] = {"12345Abcde"};

            //Insertamos los datos creando el objeto
            for (int i = 0; i < usuarios.length; i++) {
                Usuario usuario = new Usuario(i + 1, usuarios[i], contrasena[i]);
                fileobj.writeObject(usuario);
            }

            fileobj.close();
            System.out.println("Se ha creado el DAT de los usuarios.");

            //Creamos los flujos de salida
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobjin = new ObjectInputStream(filein);

            //Mostramos los datos para saber si se ha generado bien el archivo
            Usuario usuario;
            while (((usuario = (Usuario) fileobjin.readObject()) != null)) {
                System.out.println(usuario.getUsuario());
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
