package estructuras;

public class MatrizOrtogonal {
    private NodoMatriz inicio;
    private NodoMatriz[][] referencias;
    private int filas;
    private int columnas;

    public MatrizOrtogonal(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        referencias = new NodoMatriz[filas][columnas];
        construir();
    }

    private void construir() {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                referencias[f][c] = new NodoMatriz(f, c);
            }
        }
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (f > 0) referencias[f][c].setArriba(referencias[f - 1][c]);
                if (f < filas - 1) referencias[f][c].setAbajo(referencias[f + 1][c]);
                if (c > 0) referencias[f][c].setIzquierda(referencias[f][c - 1]);
                if (c < columnas - 1) referencias[f][c].setDerecha(referencias[f][c + 1]);
            }
        }
        inicio = referencias[0][0];
    }

    public boolean agregarPrimeraVacia(Object dato) {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (referencias[f][c].getDato() == null) {
                    referencias[f][c].setDato(dato);
                    return true;
                }
            }
        }
        return false;
    }

    public NodoMatriz obtenerNodo(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) return null;
        return referencias[fila][columna];
    }

    public Object obtenerDato(int fila, int columna) {
        NodoMatriz nodo = obtenerNodo(fila, columna);
        return nodo == null ? null : nodo.getDato();
    }

    public void colocarDato(int fila, int columna, Object dato) {
        NodoMatriz nodo = obtenerNodo(fila, columna);
        if (nodo != null) nodo.setDato(dato);
    }

    public void intercambiar(int fila1, int col1, int fila2, int col2) {
        NodoMatriz a = obtenerNodo(fila1, col1);
        NodoMatriz b = obtenerNodo(fila2, col2);
        if (a != null && b != null) {
            Object temp = a.getDato();
            a.setDato(b.getDato());
            b.setDato(temp);
        }
    }

    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
    public NodoMatriz getInicio() { return inicio; }
}
