package estructuras;

public class ListaEnlazadaSimple {
    private NodoSimple cabeza;
    private int tamanio;

    public ListaEnlazadaSimple() {
        cabeza = null;
        tamanio = 0;
    }

    public void agregar(Object dato) {
        NodoSimple nuevo = new NodoSimple(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoSimple actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamanio++;
    }

    public Object obtener(int indice) {
        if (indice < 0 || indice >= tamanio) return null;
        NodoSimple actual = cabeza;
        int contador = 0;
        while (actual != null) {
            if (contador == indice) return actual.getDato();
            contador++;
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean eliminar(int indice) {
        if (indice < 0 || indice >= tamanio || cabeza == null) return false;
        if (indice == 0) {
            cabeza = cabeza.getSiguiente();
            tamanio--;
            return true;
        }
        NodoSimple anterior = cabeza;
        int contador = 0;
        while (anterior != null && contador < indice - 1) {
            anterior = anterior.getSiguiente();
            contador++;
        }
        if (anterior != null && anterior.getSiguiente() != null) {
            anterior.setSiguiente(anterior.getSiguiente().getSiguiente());
            tamanio--;
            return true;
        }
        return false;
    }

    public int getTamanio() { return tamanio; }
    public NodoSimple getCabeza() { return cabeza; }
    public boolean estaVacia() { return tamanio == 0; }
}
