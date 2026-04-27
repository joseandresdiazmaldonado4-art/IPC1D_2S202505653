package logica;

import estructuras.Cola;
import estructuras.ListaEnlazadaSimple;
import modelo.Torneo;
import persistencia.PersistenciaTorneos;

public class GestorTorneos {
    private ListaEnlazadaSimple torneos;
    private PersistenciaTorneos persistencia;

    public GestorTorneos() {
        persistencia = new PersistenciaTorneos();
        torneos = persistencia.cargar();
    }

    public ListaEnlazadaSimple getTorneos() { return torneos; }
    public void guardar() { persistencia.guardar(torneos); }

    public String simularVenta(final Torneo torneo, int cantidadSolicitada) {
        if (torneo == null) return "Seleccione un torneo.";
        if (cantidadSolicitada <= 0) return "Ingrese una cantidad valida.";
        int maximo = torneo.getTicketsRestantes();
        if (cantidadSolicitada > maximo) cantidadSolicitada = maximo;
        final Cola cola = new Cola();
        for (int i = 1; i <= cantidadSolicitada; i++) {
            cola.encolar("Ticket " + i);
        }
        final int[] vendidosPorHilo = new int[2];
        Thread hilo1 = new Thread(new Runnable() {
            public void run() {
                while (cola.desencolar() != null) {
                    if (torneo.venderUno()) vendidosPorHilo[0]++;
                    dormir();
                }
            }
        });
        Thread hilo2 = new Thread(new Runnable() {
            public void run() {
                while (cola.desencolar() != null) {
                    if (torneo.venderUno()) vendidosPorHilo[1]++;
                    dormir();
                }
            }
        });
        hilo1.start();
        hilo2.start();
        try {
            hilo1.join();
            hilo2.join();
        } catch (Exception e) { }
        guardar();
        return "Venta simulada con 2 Threads. Hilo 1 vendio: " + vendidosPorHilo[0] + " | Hilo 2 vendio: " + vendidosPorHilo[1];
    }

    private void dormir() {
        try { Thread.sleep(35); } catch (Exception e) { }
    }
}
