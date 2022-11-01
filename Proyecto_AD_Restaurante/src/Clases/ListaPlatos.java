package Clases;

import java.util.ArrayList;
import java.util.List;

//Clase que se usa para pasar los .dat de platos a XML con XStream
//Solo contiene una lista
public class ListaPlatos {
    private List<Plato> lista = new ArrayList<Plato>();

    //Contructor
    public ListaPlatos() {
    }

    public void add(Plato pla) {
        lista.add(pla);
    }
}
