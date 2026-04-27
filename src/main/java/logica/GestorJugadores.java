package logica;

import estructuras.ListaEnlazadaSimple;
import modelo.Jugador;

public class GestorJugadores {
    private ListaEnlazadaSimple jugadores;

    public GestorJugadores() {
        jugadores = new ListaEnlazadaSimple();
        Jugador a = new Jugador("Andres Alvarez", "202400001", 150, 299.99);
        Jugador b = new Jugador("Maria Lopez", "202400002", 720, 849.50);
        Jugador c = new Jugador("Carlos Ramirez", "202400003", 1850, 1599.00);
        jugadores.agregar(a);
        jugadores.agregar(b);
        jugadores.agregar(c);
    }

    public ListaEnlazadaSimple getJugadores() { return jugadores; }
    public void agregar(Jugador jugador) { jugadores.agregar(jugador); }
    public void eliminar(int indice) { jugadores.eliminar(indice); }
}
