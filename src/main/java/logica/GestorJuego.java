package logica;

import estructuras.ListaEnlazadaSimple;
import modelo.Juego;
import persistencia.PersistenciaCatalogo;

public class GestorJuego {
    private ListaEnlazadaSimple juegos;
    private PersistenciaCatalogo persistencia;

    public GestorJuego() {
        persistencia = new PersistenciaCatalogo();
        juegos = persistencia.cargarCatalogo();
    }

    public ListaEnlazadaSimple getJuegos() { return juegos; }

    public Juego buscarPorCodigo(String codigo) {
        for (int i = 0; i < juegos.getTamanio(); i++) {
            Juego j = (Juego) juegos.obtener(i);
            if (j.getCodigo().equalsIgnoreCase(codigo)) return j;
        }
        return null;
    }

    public void agregarJuego(Juego juego) { juegos.agregar(juego); guardar(); }
    public void guardar() { persistencia.guardarCatalogo(juegos); }

    public String generarNuevoCodigo() {
        int mayor = 0;
        for (int i = 0; i < juegos.getTamanio(); i++) {
            Juego j = (Juego) juegos.obtener(i);
            try {
                int n = Integer.parseInt(j.getCodigo().replace("J", ""));
                if (n > mayor) mayor = n;
            } catch (Exception e) { }
        }
        int nuevo = mayor + 1;
        if (nuevo < 10) return "J00" + nuevo;
        if (nuevo < 100) return "J0" + nuevo;
        return "J" + nuevo;
    }
}
