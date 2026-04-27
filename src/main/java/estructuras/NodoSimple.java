package estructuras;

public class NodoSimple {
    private Object dato;
    private NodoSimple siguiente;

    public NodoSimple(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Object getDato() { return dato; }
    public void setDato(Object dato) { this.dato = dato; }
    public NodoSimple getSiguiente() { return siguiente; }
    public void setSiguiente(NodoSimple siguiente) { this.siguiente = siguiente; }
}
