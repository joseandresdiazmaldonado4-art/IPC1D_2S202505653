package estructuras;

public class NodoCola {
    private Object dato;
    private NodoCola siguiente;

    public NodoCola(Object dato) {
        this.dato = dato;
    }

    public Object getDato() { return dato; }
    public NodoCola getSiguiente() { return siguiente; }
    public void setSiguiente(NodoCola siguiente) { this.siguiente = siguiente; }
}
