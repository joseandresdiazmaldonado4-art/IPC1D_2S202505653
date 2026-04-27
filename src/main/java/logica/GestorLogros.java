package logica;

import modelo.Jugador;
import javax.swing.JOptionPane;

public class GestorLogros {
    private String[] nombres = {
        "Primera compra", "Coleccionista", "Comprador frecuente", "Fan de torneos",
        "Nivel 2 alcanzado", "Nivel 3 alcanzado", "Gran inversionista", "Album completo"
    };

    public void revisarCompra(Jugador jugador, int cantidadJuegos, double total) {
        activar(jugador, 0);
        if (cantidadJuegos >= 3) activar(jugador, 2);
        if (jugador.getNivel() >= 2) activar(jugador, 4);
        if (jugador.getNivel() >= 3) activar(jugador, 5);
        if (jugador.getGastado() + total >= 1000) activar(jugador, 6);
    }

    public void revisarAlbum(Jugador jugador, int cantidadCartas) {
        if (cantidadCartas >= 5) activar(jugador, 1);
        if (cantidadCartas >= 24) activar(jugador, 7);
    }

    public void revisarTorneo(Jugador jugador) { activar(jugador, 3); }

    private void activar(Jugador jugador, int indice) {
        if (!jugador.tieneLogro(indice)) {
            jugador.activarLogro(indice);
            JOptionPane.showMessageDialog(null, "Logro desbloqueado: " + nombres[indice], "Logro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public String getNombre(int i) { return nombres[i]; }
}
