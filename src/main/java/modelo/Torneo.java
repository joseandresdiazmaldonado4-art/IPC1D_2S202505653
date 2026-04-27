package modelo;

public class Torneo {
    private String codigo;
    private String nombre;
    private String juego;
    private int ticketsDisponibles;
    private int ticketsVendidos;

    public Torneo(String codigo, String nombre, String juego, int ticketsDisponibles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.juego = juego;
        this.ticketsDisponibles = ticketsDisponibles;
        this.ticketsVendidos = 0;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getJuego() { return juego; }
    public int getTicketsDisponibles() { return ticketsDisponibles; }
    public int getTicketsVendidos() { return ticketsVendidos; }
    public int getTicketsRestantes() { return ticketsDisponibles - ticketsVendidos; }
    public void setTicketsDisponibles(int ticketsDisponibles) { this.ticketsDisponibles = ticketsDisponibles; if (ticketsVendidos > ticketsDisponibles) ticketsVendidos = ticketsDisponibles; }
    public synchronized boolean venderUno() { if (ticketsVendidos < ticketsDisponibles) { ticketsVendidos++; return true; } return false; }
    public String toArchivo() { return codigo + "|" + nombre + "|" + juego + "|" + ticketsDisponibles + "|" + ticketsVendidos; }
}
