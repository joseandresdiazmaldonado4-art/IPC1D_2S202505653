package modelo;

public class ItemCarrito {
    private Juego juego;
    private int cantidad;

    public ItemCarrito(Juego juego, int cantidad) {
        this.juego = juego;
        this.cantidad = cantidad;
    }

    public Juego getJuego() { return juego; }
    public int getCantidad() { return cantidad; }
    public void sumarCantidad(int cantidad) { this.cantidad += cantidad; }
    public double getSubtotal() { return juego.getPrecio() * cantidad; }
}
