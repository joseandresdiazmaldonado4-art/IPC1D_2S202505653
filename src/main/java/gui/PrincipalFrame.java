package gui;

import logica.*;
import modelo.Jugador;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.*;

public class PrincipalFrame extends JFrame {
    private Jugador jugador;
    private GestorJuego gestorJuego;
    private GestorAlbum gestorAlbum;
    private GestorTorneos gestorTorneos;
    private GestorVentas gestorVentas;
    private GestorLogros gestorLogros;
    private CardLayout cardLayout;
    private JPanel panelCards;
    private JLabel lblEstado;
    private PanelTienda panelTienda;
    private PanelAlbum panelAlbum;
    private PanelTorneos panelTorneos;
    private PanelLogros panelLogros;
    private PanelReportes panelReportes;

    public PrincipalFrame(Jugador jugador) {
        this.jugador = jugador;
        gestorJuego = new GestorJuego();
        gestorAlbum = new GestorAlbum();
        gestorTorneos = new GestorTorneos();
        gestorVentas = new GestorVentas();
        gestorLogros = new GestorLogros();
        setTitle("GameZone Pro - " + jugador.getNombre());
        setSize(1120, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        construir();
    }

    private void construir() {
        setLayout(new BorderLayout());
        JPanel superior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnTienda = new JButton("Tienda");
        JButton btnAlbum = new JButton("Album");
        JButton btnTorneos = new JButton("Torneos");
        JButton btnLogros = new JButton("Logros y Leaderboard");
        JButton btnReportes = new JButton("Reportes");
        JButton btnEstudiante = new JButton("Ver Datos del Estudiante");
        JButton btnSalir = new JButton("Salir");
        JButton[] botones = {btnTienda, btnAlbum, btnTorneos, btnLogros, btnReportes, btnEstudiante, btnSalir};
        for (int i = 0; i < botones.length; i++) { EstiloSwing.boton(botones[i]); superior.add(botones[i]); }
        add(superior, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        panelCards = new JPanel(cardLayout);
        panelTienda = new PanelTienda(jugador, gestorJuego, gestorVentas, gestorLogros, this);
        panelAlbum = new PanelAlbum(jugador, gestorAlbum, gestorLogros);
        panelTorneos = new PanelTorneos(jugador, gestorTorneos, gestorLogros);
        panelLogros = new PanelLogros(jugador);
        panelReportes = new PanelReportes(gestorJuego, gestorVentas, gestorAlbum, gestorTorneos);
        panelCards.add(panelTienda, "TIENDA");
        panelCards.add(panelAlbum, "ALBUM");
        panelCards.add(panelTorneos, "TORNEOS");
        panelCards.add(panelLogros, "LOGROS");
        panelCards.add(panelReportes, "REPORTES");
        add(panelCards, BorderLayout.CENTER);

        lblEstado = new JLabel();
        actualizarEstado();
        add(lblEstado, BorderLayout.SOUTH);

        btnTienda.addActionListener(e -> { panelTienda.refrescarTodo(); cardLayout.show(panelCards, "TIENDA"); actualizarEstado(); });
        btnAlbum.addActionListener(e -> { panelAlbum.refrescar(); cardLayout.show(panelCards, "ALBUM"); actualizarEstado(); });
        btnTorneos.addActionListener(e -> { panelTorneos.refrescar(); cardLayout.show(panelCards, "TORNEOS"); actualizarEstado(); });
        btnLogros.addActionListener(e -> { panelLogros.refrescar(); cardLayout.show(panelCards, "LOGROS"); actualizarEstado(); });
        btnReportes.addActionListener(e -> cardLayout.show(panelCards, "REPORTES"));
        btnEstudiante.addActionListener(e -> JOptionPane.showMessageDialog(this, "Estudiante\nFacultad de Ingenieria\nEscuela de Ciencias y Sistemas"));
        btnSalir.addActionListener(e -> System.exit(0));
    }

    public void actualizarEstado() {
        lblEstado.setText("  Jugador: " + jugador.getNombre() + " | Carne: " + jugador.getCarne() + " | " + jugador.getNivelTexto() + " | XP: " + jugador.getXp());
    }
}
