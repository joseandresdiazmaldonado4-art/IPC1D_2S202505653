package gui;

import logica.GestorAlbum;
import logica.GestorLogros;
import modelo.Carta;
import modelo.Jugador;
import java.awt.*;
import javax.swing.*;

public class PanelAlbum extends JPanel {
    private Jugador jugador;
    private GestorAlbum gestorAlbum;
    private GestorLogros gestorLogros;
    private JButton[][] botones;
    private int selFila = -1;
    private int selCol = -1;
    private JTextField txtBuscar;

    public PanelAlbum(Jugador jugador, GestorAlbum gestorAlbum, GestorLogros gestorLogros) {
        this.jugador = jugador;
        this.gestorAlbum = gestorAlbum;
        this.gestorLogros = gestorLogros;
        construir();
        refrescar();
    }

    private void construir() {
        setLayout(new BorderLayout(8, 8));
        EstiloSwing.panel(this);
        JPanel superior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAgregar = new JButton("Agregar carta");
        JButton btnIntercambiar = new JButton("Intercambiar seleccionadas");
        txtBuscar = new JTextField(18);
        JButton btnBuscar = new JButton("Buscar / Resaltar");
        JButton btnLimpiar = new JButton("Limpiar resaltado");
        superior.add(btnAgregar); superior.add(btnIntercambiar); superior.add(new JLabel("Buscar:")); superior.add(txtBuscar); superior.add(btnBuscar); superior.add(btnLimpiar);
        add(superior, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(4, 6, 6, 6));
        botones = new JButton[4][6];
        for (int f = 0; f < 4; f++) {
            for (int c = 0; c < 6; c++) {
                final int ff = f, cc = c;
                botones[f][c] = new JButton();
                botones[f][c].setPreferredSize(new Dimension(120, 90));
                botones[f][c].addActionListener(e -> seleccionar(ff, cc));
                grid.add(botones[f][c]);
            }
        }
        add(grid, BorderLayout.CENTER);
        btnAgregar.addActionListener(e -> agregarCarta());
        btnIntercambiar.addActionListener(e -> seleccionarIntercambio());
        btnBuscar.addActionListener(e -> buscar());
        btnLimpiar.addActionListener(e -> refrescar());
    }

    private void seleccionar(int f, int c) {
        selFila = f; selCol = c;
        refrescar();
        botones[f][c].setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
    }

    private void seleccionarIntercambio() {
        if (selFila < 0) { JOptionPane.showMessageDialog(this, "Seleccione la primera celda."); return; }
        String texto = JOptionPane.showInputDialog(this, "Ingrese destino como fila,columna. Ejemplo: 2,3");
        if (texto == null) return;
        try {
            String[] p = texto.split(",");
            int f2 = Integer.parseInt(p[0].trim()) - 1;
            int c2 = Integer.parseInt(p[1].trim()) - 1;
            gestorAlbum.intercambiar(selFila, selCol, f2, c2);
            selFila = -1; selCol = -1;
            refrescar();
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Formato invalido."); }
    }

    private void agregarCarta() {
        String codigo = JOptionPane.showInputDialog(this, "Codigo de carta:"); if (codigo == null || codigo.trim().length() == 0) return;
        String nombre = JOptionPane.showInputDialog(this, "Nombre de carta:"); if (nombre == null || nombre.trim().length() == 0) return;
        String rareza = JOptionPane.showInputDialog(this, "Rareza:", "Comun"); if (rareza == null) return;
        boolean ok = gestorAlbum.agregarCarta(new Carta(codigo, nombre, rareza));
        if (!ok) JOptionPane.showMessageDialog(this, "Album lleno.");
        gestorLogros.revisarAlbum(jugador, gestorAlbum.contarCartas());
        refrescar();
    }

    private void buscar() {
        refrescar();
        String criterio = txtBuscar.getText().toLowerCase().trim();
        if (criterio.length() == 0) return;
        for (int f = 0; f < 4; f++) {
            for (int c = 0; c < 6; c++) {
                Object dato = gestorAlbum.getMatriz().obtenerDato(f, c);
                if (dato instanceof Carta) {
                    Carta carta = (Carta) dato;
                    String texto = (carta.getCodigo() + " " + carta.getNombre() + " " + carta.getRareza()).toLowerCase();
                    if (texto.contains(criterio)) botones[f][c].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                }
            }
        }
    }

    public void refrescar() {
        for (int f = 0; f < 4; f++) {
            for (int c = 0; c < 6; c++) {
                Object dato = gestorAlbum.getMatriz().obtenerDato(f, c);
                if (dato instanceof Carta) {
                    Carta carta = (Carta) dato;
                    botones[f][c].setText("<html><center>" + carta.getCodigo() + "<br>" + carta.getNombre() + "<br>" + carta.getRareza() + "</center></html>");
                } else {
                    botones[f][c].setText("<html><center>Vacia<br>Fila " + (f + 1) + ", Col " + (c + 1) + "</center></html>");
                }
                botones[f][c].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            }
        }
    }
}
