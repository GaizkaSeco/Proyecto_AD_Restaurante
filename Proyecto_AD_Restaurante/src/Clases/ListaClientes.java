package Clases;

import java.util.ArrayList;
import java.util.List;

//Clase que se usa para pasar los .dat de clientes a XML con XStream
//Solo contiene una lista
public class ListaClientes {
    private List<Cliente> lista = new ArrayList<Cliente>();

    //Contructor
    public ListaClientes() {
    }

    public void add(Cliente cli) {
        lista.add(cli);
    }
}
