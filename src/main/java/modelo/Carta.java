package modelo;

public class Carta {
    private String codigo;
    private String nombre;
    private String rareza;

    public Carta(String codigo, String nombre, String rareza) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.rareza = rareza;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getRareza() { return rareza; }
    public String toArchivo(int fila, int columna) { return fila + "|" + columna + "|" + codigo + "|" + nombre + "|" + rareza; }
    public String toString() { return codigo + " - " + nombre + " (" + rareza + ")"; }
}
