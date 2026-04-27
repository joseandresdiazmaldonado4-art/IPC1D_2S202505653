package logica;

import estructuras.ListaEnlazadaSimple;
import modelo.Venta;

public class GestorVentas {
    private ListaEnlazadaSimple ventas = new ListaEnlazadaSimple();
    public void agregar(Venta venta) { ventas.agregar(venta); }
    public ListaEnlazadaSimple getVentas() { return ventas; }
}
