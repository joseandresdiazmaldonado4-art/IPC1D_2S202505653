package gamezonepro.gamificacion;

import javax.swing.*;

public class SistemaGamificacion {

    private int xpActual;
    private int xpNecesaria;
    private JProgressBar barraProgreso;
    private int nivel;

    public SistemaGamificacion(JProgressBar barraProgreso) {
        this.xpActual = 0;
        this.xpNecesaria = 100; // XP necesario para subir de nivel
        this.nivel = 1;
        this.barraProgreso = barraProgreso;
        actualizarBarra();
    }

    public void agregarXP(int xp) {
        xpActual += xp;

        if (xpActual >= xpNecesaria) {
            xpActual = 0;
            nivel++;
            xpNecesaria += 50; // Aumenta la XP necesaria por cada nivel
            JOptionPane.showMessageDialog(null, "¡Felicidades! Has alcanzado el nivel " + nivel);
        }

        actualizarBarra();
    }

    private void actualizarBarra() {
        barraProgreso.setValue((int) ((double) xpActual / xpNecesaria * 100));
        barraProgreso.setString("Nivel: " + nivel + " - XP: " + xpActual + "/" + xpNecesaria);
    }

    public int getNivel() {
        return nivel;
    }

    public int getXPActual() {
        return xpActual;
    }
}