package gui;

import logica.GestorJugadores;
import modelo.Jugador;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SeleccionPerfilFrame extends JFrame {
    private GestorJugadores gestor;
    private JTable tabla;
    private DefaultTableModel modelo;

    public SeleccionPerfilFrame() {
        gestor = new GestorJugadores();
        setTitle("GameZone Pro - Seleccion de Perfil");
        setSize(650, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        construir();
        cargarTabla();
    }

    private void construir() {
        JPanel principal = new JPanel(new BorderLayout(8, 8));
        EstiloSwing.panel(principal);
        modelo = new DefaultTableModel(new Object[]{"Nombre", "Carne", "Nivel", "XP"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modelo);
        principal.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnSeleccionar = new JButton("Seleccionar Jugador");
        JButton btnNuevo = new JButton("Nuevo Jugador");
        JButton btnEliminar = new JButton("Eliminar");
        EstiloSwing.boton(btnSeleccionar); EstiloSwing.boton(btnNuevo); EstiloSwing.boton(btnEliminar);
        botones.add(btnSeleccionar); botones.add(btnNuevo); botones.add(btnEliminar);
        principal.add(botones, BorderLayout.SOUTH);
        add(principal);

        btnSeleccionar.addActionListener(e -> seleccionar());
        btnNuevo.addActionListener(e -> nuevo());
        btnEliminar.addActionListener(e -> eliminar());
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        for (int i = 0; i < gestor.getJugadores().getTamanio(); i++) {
            Jugador j = (Jugador) gestor.getJugadores().obtener(i);
            modelo.addRow(new Object[]{j.getNombre(), j.getCarne(), j.getNivelTexto(), j.getXp()});
        }
    }

    private void seleccionar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Seleccione un jugador."); return; }
        Jugador jugador = (Jugador) gestor.getJugadores().obtener(fila);
        new PrincipalFrame(jugador).setVisible(true);
        dispose();
    }

    private void nuevo() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del jugador:");
        if (nombre == null || nombre.trim().length() == 0) return;
        String carne = "2024" + (100 + gestor.getJugadores().getTamanio());
        gestor.agregar(new Jugador(nombre.trim(), carne, 0, 0));
        cargarTabla();
    }

    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Seleccione un jugador."); return; }
        gestor.eliminar(fila);
        cargarTabla();
    }
}
