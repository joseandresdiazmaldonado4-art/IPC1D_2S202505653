package modelo;

public class Venta {
    private String jugador;
    private String codigoJuego;
    private String nombreJuego;
    private int cantidad;
    private double total;
    private String fecha;

    public Venta(String jugador, String codigoJuego, String nombreJuego, int cantidad, double total, String fecha) {
        this.jugador = jugador;
        this.codigoJuego = codigoJuego;
        this.nombreJuego = nombreJuego;
        this.cantidad = cantidad;
        this.total = total;
        this.fecha = fecha;
    }

    public String getJugador() { return jugador; }
    public String getCodigoJuego() { return codigoJuego; }
    public String getNombreJuego() { return nombreJuego; }
    public int getCantidad() { return cantidad; }
    public double getTotal() { return total; }
    public String getFecha() { return fecha; }
}
