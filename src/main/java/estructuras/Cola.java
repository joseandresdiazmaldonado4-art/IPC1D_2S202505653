package estructuras;

public class Cola {
    private NodoCola frente;
    private NodoCola fin;
    private int tamanio;

    public synchronized void encolar(Object dato) {
        NodoCola nuevo = new NodoCola(dato);
        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        tamanio++;
    }

    public synchronized Object desencolar() {
        if (frente == null) return null;
        Object dato = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) fin = null;
        tamanio--;
        return dato;
    }

    public synchronized boolean estaVacia() { return frente == null; }
    public synchronized int getTamanio() { return tamanio; }
}
