package gamezonepro.estructuras;

public class ListaEnlazadaSimple<T> {

    private NodoSimple<T> cabeza;
    private NodoSimple<T> cola;
    private int tamanio;

    public ListaEnlazadaSimple() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }

    public void agregar(T dato) {
        NodoSimple<T> nuevoNodo = new NodoSimple<T>(dato);

        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            cola.setSiguiente(nuevoNodo);
            cola = nuevoNodo;
        }

        tamanio++;
    }

    public void insertarInicio(T dato) {
        NodoSimple<T> nuevoNodo = new NodoSimple<T>(dato);

        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            nuevoNodo.setSiguiente(cabeza);
            cabeza = nuevoNodo;
        }

        tamanio++;
    }

    public T buscar(CriterioBusqueda<T> criterioBusqueda) {
        NodoSimple<T> actual = cabeza;

        while (actual != null) {
            if (criterioBusqueda.cumple(actual.getDato())) {
                return actual.getDato();
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

    public boolean eliminar(CriterioBusqueda<T> criterioBusqueda) {
        if (cabeza == null) {
            return false;
        }

        if (criterioBusqueda.cumple(cabeza.getDato())) {
            cabeza = cabeza.getSiguiente();

            if (cabeza == null) {
                cola = null;
            }

            tamanio--;
            return true;
        }

        NodoSimple<T> anterior = cabeza;
        NodoSimple<T> actual = cabeza.getSiguiente();

        while (actual != null) {
            if (criterioBusqueda.cumple(actual.getDato())) {
                anterior.setSiguiente(actual.getSiguiente());

                if (actual == cola) {
                    cola = anterior;
                }

                tamanio--;
                return true;
            }

            anterior = actual;
            actual = actual.getSiguiente();
        }

        return false;
    }

    public void limpiar() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public NodoSimple<T> getCabeza() {
        return cabeza;
    }
}