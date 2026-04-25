package gamezonepro.estructuras;

public class NodoMatriz<T> {

    private T dato;
    private NodoMatriz<T> arriba;
    private NodoMatriz<T> abajo;
    private NodoMatriz<T> izquierda;
    private NodoMatriz<T> derecha;

    public NodoMatriz(T dato) {
        this.dato = dato;
        this.arriba = null;
        this.abajo = null;
        this.izquierda = null;
        this.derecha = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoMatriz<T> getArriba() {
        return arriba;
    }

    public void setArriba(NodoMatriz<T> arriba) {
        this.arriba = arriba;
    }

    public NodoMatriz<T> getAbajo() {
        return abajo;
    }

    public void setAbajo(NodoMatriz<T> abajo) {
        this.abajo = abajo;
    }

    public NodoMatriz<T> getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoMatriz<T> izquierda) {
        this.izquierda = izquierda;
    }

    public NodoMatriz<T> getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoMatriz<T> derecha) {
        this.derecha = derecha;
    }
}