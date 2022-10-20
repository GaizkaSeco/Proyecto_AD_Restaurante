package Clases;

import InterfazGrafica.VentanaEmpleados;

import javax.swing.*;

public class Hilo extends Thread {
    VentanaEmpleados v = new VentanaEmpleados();
    public Hilo() {

    }

    @Override
    public void run() {
        v.modificarTabla();
    }
}
