package Clases;

import java.util.ArrayList;
import java.util.List;

//Clase que se usa para pasar los .dat de empleados a XML con XStream
//Solo contiene una lista
public class ListaEmpleados {
    private List<Empleado> lista = new ArrayList<Empleado>();

    //Constructor
    public ListaEmpleados() {
    }

    public void add(Empleado emp) {
        lista.add(emp);
    }
}
