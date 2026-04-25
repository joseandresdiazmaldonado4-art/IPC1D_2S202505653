package gamezonepro.gamificacion;

import gamezonepro.estructuras.ListaEnlazadaSimple;
import gamezonepro.estructuras.NodoSimple;

public class Leaderboard {

    private ListaEnlazadaSimple<String> jugadores;

    public Leaderboard() {
        jugadores = new ListaEnlazadaSimple<>();
    }

    public void agregarJugador(String jugador) {
        jugadores.agregar(jugador);
    }

    public void ordenarLeaderboard() {
        // Método de ordenamiento manual (por ejemplo, BubbleSort)
        NodoSimple<String> nodoActual;
        NodoSimple<String> nodoSiguiente;
        String temp;

        for (int i = 0; i < jugadores.getTamanio(); i++) {
            nodoActual = jugadores.getCabeza();
            nodoSiguiente = nodoActual.getSiguiente();

            for (int j = 0; j < jugadores.getTamanio() - 1; j++) {
                if (nodoActual.getDato().compareTo(nodoSiguiente.getDato()) > 0) {
                    temp = nodoActual.getDato();
                    nodoActual.setDato(nodoSiguiente.getDato());
                    nodoSiguiente.setDato(temp);
                }
                nodoActual = nodoSiguiente;
                nodoSiguiente = nodoSiguiente.getSiguiente();
            }
        }
    }

    public void mostrarLeaderboard() {
        NodoSimple<String> nodoActual = jugadores.getCabeza();

        while (nodoActual != null) {
            System.out.println(nodoActual.getDato());
            nodoActual = nodoActual.getSiguiente();
        }
    }
}