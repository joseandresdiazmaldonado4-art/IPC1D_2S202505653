package logica;

import estructuras.MatrizOrtogonal;
import modelo.Carta;
import modelo.Jugador;
import persistencia.PersistenciaAlbum;

public class GestorAlbum {
    private MatrizOrtogonal matriz;
    private PersistenciaAlbum persistencia;
    private int contadorCartas;

    public GestorAlbum() {
        matriz = new MatrizOrtogonal(4, 6);
        persistencia = new PersistenciaAlbum();
        persistencia.cargar(matriz);
        contadorCartas = contarCartas();
    }

    public MatrizOrtogonal getMatriz() { return matriz; }

    public boolean agregarCarta(Carta carta) {
        boolean ok = matriz.agregarPrimeraVacia(carta);
        if (ok) {
            contadorCartas = contarCartas();
            persistencia.guardar(matriz);
        }
        return ok;
    }

    public void intercambiar(int f1, int c1, int f2, int c2) {
        matriz.intercambiar(f1, c1, f2, c2);
        persistencia.guardar(matriz);
    }

    public int contarCartas() {
        int total = 0;
        for (int f = 0; f < matriz.getFilas(); f++) {
            for (int c = 0; c < matriz.getColumnas(); c++) {
                if (matriz.obtenerDato(f, c) != null) total++;
            }
        }
        return total;
    }
}
