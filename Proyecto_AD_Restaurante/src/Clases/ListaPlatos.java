package Clases;

import java.util.ArrayList;
import java.util.List;

public class ListaPlatos {
    private List<Plato> lista = new ArrayList<Plato>();

    public ListaPlatos() {
    }

    public void add(Plato pla) {
        lista.add(pla);
    }
}
