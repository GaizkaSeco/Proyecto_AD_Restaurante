package Clases;

import java.util.ArrayList;
import java.util.List;

public class ListaProductos {
    private List<Producto> lista = new ArrayList<Producto>();

    public ListaProductos() {
    }

    public void add(Producto pro) {
        lista.add(pro);
    }
}
