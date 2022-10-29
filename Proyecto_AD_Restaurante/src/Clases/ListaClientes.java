package Clases;

import java.util.ArrayList;
import java.util.List;

public class ListaClientes {
    private List<Cliente> lista = new ArrayList<Cliente>();

    public ListaClientes() {
    }

    public void add(Cliente cli) {
        lista.add(cli);
    }
}
