package gui;

import logica.*;
import java.awt.*;
import javax.swing.*;

public class PanelReportes extends JPanel {
    private GestorJuego gestorJuego;
    private GestorVentas gestorVentas;
    private GestorAlbum gestorAlbum;
    private GestorTorneos gestorTorneos;
    private JTextArea salida;
    private ReporteHTML reporte;

    public PanelReportes(GestorJuego gestorJuego, GestorVentas gestorVentas, GestorAlbum gestorAlbum, GestorTorneos gestorTorneos) {
        this.gestorJuego = gestorJuego;
        this.gestorVentas = gestorVentas;
        this.gestorAlbum = gestorAlbum;
        this.gestorTorneos = gestorTorneos;
        reporte = new ReporteHTML();
        construir();
    }

    private void construir() {
        setLayout(new BorderLayout(8, 8));
        EstiloSwing.panel(this);
        JPanel botones = new JPanel(new GridLayout(2, 2, 8, 8));
        JButton btnInv = new JButton("Reporte Inventario");
        JButton btnVentas = new JButton("Reporte Ventas");
        JButton btnAlbum = new JButton("Reporte Album");
        JButton btnTorneos = new JButton("Reporte Torneos");
        botones.add(btnInv); botones.add(btnVentas); botones.add(btnAlbum); botones.add(btnTorneos);
        add(botones, BorderLayout.NORTH);
        salida = new JTextArea(); salida.setEditable(false);
        add(new JScrollPane(salida), BorderLayout.CENTER);
        btnInv.addActionListener(e -> escribir(reporte.inventario(gestorJuego.getJuegos())));
        btnVentas.addActionListener(e -> escribir(reporte.ventas(gestorVentas.getVentas())));
        btnAlbum.addActionListener(e -> escribir(reporte.album(gestorAlbum.getMatriz())));
        btnTorneos.addActionListener(e -> escribir(reporte.torneos(gestorTorneos.getTorneos())));
    }

    private void escribir(String ruta) {
        salida.append("Reporte generado: " + ruta + "\n");
        JOptionPane.showMessageDialog(this, "Reporte generado:\n" + ruta);
    }
}
