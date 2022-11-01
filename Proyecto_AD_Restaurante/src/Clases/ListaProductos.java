package Clases;

import java.util.ArrayList;
import java.util.List;

//Clase que se usa para pasar los .dat de productos a XML con XStream
//Solo contiene una lista
public class ListaProductos {
    private List<Producto> lista = new ArrayList<Producto>();

    //Contructor
    public ListaProductos() {
    }

    public void add(Producto pro) {
        lista.add(pro);
    }
}
