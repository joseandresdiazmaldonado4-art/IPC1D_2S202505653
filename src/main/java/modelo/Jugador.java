package modelo;

public class Jugador {
    private String nombre;
    private String carne;
    private int xp;
    private double gastado;
    private boolean[] logros;

    public Jugador(String nombre, String carne, int xp, double gastado) {
        this.nombre = nombre;
        this.carne = carne;
        this.xp = xp;
        this.gastado = gastado;
        this.logros = new boolean[8];
    }

    public String getNombre() { return nombre; }
    public String getCarne() { return carne; }
    public int getXp() { return xp; }
    public double getGastado() { return gastado; }
    public boolean tieneLogro(int indice) { return indice >= 0 && indice < logros.length && logros[indice]; }
    public void activarLogro(int indice) { if (indice >= 0 && indice < logros.length) logros[indice] = true; }
    public void sumarXp(int cantidad) { xp += cantidad; }
    public void sumarGastado(double cantidad) { gastado += cantidad; }

    public int getNivel() {
        if (xp >= 7000) return 5;
        if (xp >= 3500) return 4;
        if (xp >= 1500) return 3;
        if (xp >= 500) return 2;
        return 1;
    }

    public String getNivelTexto() {
        int n = getNivel();
        if (n == 1) return "N1 (0-499 XP)";
        if (n == 2) return "N2 (500-1499 XP)";
        if (n == 3) return "N3 (1500-3499 XP)";
        if (n == 4) return "N4 (3500-6999 XP)";
        return "N5 (7000+ XP)";
    }
}
