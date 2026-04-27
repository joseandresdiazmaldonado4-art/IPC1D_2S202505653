package gui;

import modelo.Jugador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelLogros extends JPanel {
    private Jugador jugador;
    private JTable tblLogros;
    private DefaultTableModel modeloLogros;
    private JTable tblPodio;
    private DefaultTableModel modeloPodio;
    private String[] nombres = {
        "Primera compra", "Coleccionista", "Comprador frecuente", "Fan de torneos",
        "Nivel 2 alcanzado", "Nivel 3 alcanzado", "Gran inversionista", "Album completo"
    };

    public PanelLogros(Jugador jugador) {
        this.jugador = jugador;
        construir();
        refrescar();
    }

    private void construir() {
        setLayout(new GridLayout(1, 2, 8, 8));
        EstiloSwing.panel(this);
        modeloLogros = new DefaultTableModel(new Object[]{"Logro", "Estado"}, 0) { public boolean isCellEditable(int r, int c) { return false; } };
        tblLogros = new JTable(modeloLogros);
        modeloPodio = new DefaultTableModel(new Object[]{"Puesto", "Jugador", "XP"}, 0) { public boolean isCellEditable(int r, int c) { return false; } };
        tblPodio = new JTable(modeloPodio);
        add(new JScrollPane(tblLogros));
        add(new JScrollPane(tblPodio));
    }

    public void refrescar() {
        modeloLogros.setRowCount(0);
        for (int i = 0; i < nombres.length; i++) {
            modeloLogros.addRow(new Object[]{nombres[i], jugador.tieneLogro(i) ? "Desbloqueado" : "Pendiente"});
        }
        Jugador[] jugadores = new Jugador[3];
        jugadores[0] = jugador;
        jugadores[1] = new Jugador("Maria Lopez", "202400002", 720, 849.50);
        jugadores[2] = new Jugador("Carlos Ramirez", "202400003", 1850, 1599.00);
        bubbleSort(jugadores);
        modeloPodio.setRowCount(0);
        for (int i = 0; i < jugadores.length; i++) {
            modeloPodio.addRow(new Object[]{(i + 1) + "°", jugadores[i].getNombre(), jugadores[i].getXp()});
        }
    }

    private void bubbleSort(Jugador[] datos) {
        for (int i = 0; i < datos.length - 1; i++) {
            for (int j = 0; j < datos.length - i - 1; j++) {
                if (datos[j].getXp() < datos[j + 1].getXp()) {
                    Jugador temp = datos[j];
                    datos[j] = datos[j + 1];
                    datos[j + 1] = temp;
                }
            }
        }
    }
}
