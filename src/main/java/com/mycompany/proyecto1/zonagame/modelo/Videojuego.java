package gamezonepro.modelo;

public class Videojuego {

    private String codigo;
    private String titulo;
    private String genero;
    private String plataforma;
    private double precio;
    private int stock;

    public Videojuego(String codigo, String titulo, String genero, String plataforma, double precio, int stock) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.genero = genero;
        this.plataforma = plataforma;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void reducirStock(int cantidad) {
        stock = stock - cantidad;
    }

    public String convertirLineaTexto() {
        return codigo + "|" + titulo + "|" + genero + "|" + plataforma + "|" + precio + "|" + stock;
    }

    @Override
    public String toString() {
        return titulo + " - " + plataforma + " - Q" + precio;
    }
}