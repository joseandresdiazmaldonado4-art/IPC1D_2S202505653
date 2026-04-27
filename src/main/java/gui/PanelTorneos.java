package gui;

import logica.GestorLogros;
import logica.GestorTorneos;
import modelo.Jugador;
import modelo.Torneo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelTorneos extends JPanel {
    private Jugador jugador;
    private GestorTorneos gestorTorneos;
    private GestorLogros gestorLogros;
    private JTable tabla;
    private DefaultTableModel modelo;

    public PanelTorneos(Jugador jugador, GestorTorneos gestorTorneos, GestorLogros gestorLogros) {
        this.jugador = jugador;
        this.gestorTorneos = gestorTorneos;
        this.gestorLogros = gestorLogros;
        construir();
        refrescar();
    }

    private void construir() {
        setLayout(new BorderLayout(8, 8));
        EstiloSwing.panel(this);
        modelo = new DefaultTableModel(new Object[]{"Codigo", "Torneo", "Juego", "Tickets", "Vendidos", "Restantes"}, 0) { public boolean isCellEditable(int r, int c) { return false; } };
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnTickets = new JButton("Controlar tickets");
        JButton btnSimular = new JButton("Simular venta con 2 Threads");
        JButton btnRefrescar = new JButton("Refrescar");
        botones.add(btnTickets); botones.add(btnSimular); botones.add(btnRefrescar);
        add(botones, BorderLayout.SOUTH);
        btnTickets.addActionListener(e -> controlarTickets());
        btnSimular.addActionListener(e -> simular());
        btnRefrescar.addActionListener(e -> refrescar());
    }

    public void refrescar() {
        modelo.setRowCount(0);
        for (int i = 0; i < gestorTorneos.getTorneos().getTamanio(); i++) {
            Torneo t = (Torneo) gestorTorneos.getTorneos().obtener(i);
            modelo.addRow(new Object[]{t.getCodigo(), t.getNombre(), t.getJuego(), t.getTicketsDisponibles(), t.getTicketsVendidos(), t.getTicketsRestantes()});
        }
    }

    private Torneo seleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Seleccione un torneo."); return null; }
        return (Torneo) gestorTorneos.getTorneos().obtener(fila);
    }

    private void controlarTickets() {
        Torneo t = seleccionado(); if (t == null) return;
        try {
            int nuevo = Integer.parseInt(JOptionPane.showInputDialog(this, "Cantidad total de tickets:", t.getTicketsDisponibles()));
            if (nuevo < t.getTicketsVendidos()) { JOptionPane.showMessageDialog(this, "No puede ser menor que los tickets ya vendidos."); return; }
            t.setTicketsDisponibles(nuevo);
            gestorTorneos.guardar(); refrescar();
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Numero invalido."); }
    }

    private void simular() {
        Torneo t = seleccionado(); if (t == null) return;
        try {
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog(this, "Cuantos tickets desea simular vender?", "1"));
            String resultado = gestorTorneos.simularVenta(t, cantidad);
            gestorLogros.revisarTorneo(jugador);
            JOptionPane.showMessageDialog(this, resultado);
            refrescar();
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Numero invalido."); }
    }
}
