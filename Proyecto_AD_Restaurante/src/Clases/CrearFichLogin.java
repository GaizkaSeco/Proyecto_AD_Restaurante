package Clases;

import java.io.*;

public class CrearFichLogin {
    public static void main(String[] args) {
        File file = new File("Login.dat");
        try {
            FileOutputStream fileo = new FileOutputStream(file);
            ObjectOutputStream fileobj = new ObjectOutputStream(fileo);

            String usuarios[] = {"admin"};
            String contrasena[] = {"12345Abcde"};

            for (int i = 0; i < usuarios.length; i++) {
                Usuario usuario = new Usuario(i + 1, usuarios[i], contrasena[i]);
                fileobj.writeObject(usuario);
            }

            fileobj.close();
            System.out.println("Se ha creado el DAT de los usuarios.");

            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream fileobjin = new ObjectInputStream(filein);

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
