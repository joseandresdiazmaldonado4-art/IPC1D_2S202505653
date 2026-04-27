package gui;

import estructuras.ListaEnlazadaSimple;
import logica.GestorJuego;
import logica.GestorLogros;
import logica.GestorVentas;
import modelo.ItemCarrito;
import modelo.Juego;
import modelo.Jugador;
import modelo.Venta;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelTienda extends JPanel {
    private Jugador jugador;
    private GestorJuego gestorJuego;
    private GestorVentas gestorVentas;
    private GestorLogros gestorLogros;
    private PrincipalFrame principal;
    private ListaEnlazadaSimple carrito;
    private JPanel panelCatalogo;
    private DefaultTableModel modeloCarrito;
    private JTable tblCarrito;
    private JLabel lblTotal;

    public PanelTienda(Jugador jugador, GestorJuego gestorJuego, GestorVentas gestorVentas, GestorLogros gestorLogros, PrincipalFrame principal) {
        this.jugador = jugador;
        this.gestorJuego = gestorJuego;
        this.gestorVentas = gestorVentas;
        this.gestorLogros = gestorLogros;
        this.principal = principal;
        this.carrito = new ListaEnlazadaSimple();
        construir();
        refrescarTodo();
    }

    private void construir() {
        setLayout(new BorderLayout(8, 8));
        EstiloSwing.panel(this);
        panelCatalogo = new JPanel(new GridLayout(0, 2, 8, 8));
        JScrollPane scrollCatalogo = new JScrollPane(panelCatalogo);
        add(scrollCatalogo, BorderLayout.CENTER);

        JPanel derecha = new JPanel(new BorderLayout(5, 5));
        derecha.setPreferredSize(new Dimension(360, 0));
        modeloCarrito = new DefaultTableModel(new Object[]{"Codigo", "Juego", "Cant.", "Subtotal"}, 0) { public boolean isCellEditable(int r, int c) { return false; } };
        tblCarrito = new JTable(modeloCarrito);
        derecha.add(new JLabel("Carrito"), BorderLayout.NORTH);
        derecha.add(new JScrollPane(tblCarrito), BorderLayout.CENTER);

        JPanel acciones = new JPanel(new GridLayout(0, 1, 4, 4));
        lblTotal = new JLabel("Total: Q0.00");
        JButton btnComprar = new JButton("Comprar");
        JButton btnVaciar = new JButton("Vaciar carrito");
        JButton btnAgregarJuego = new JButton("Agregar juego");
        JButton btnEditarStock = new JButton("Editar stock");
        JButton btnEliminarJuego = new JButton("Eliminar juego");
        acciones.add(lblTotal); acciones.add(btnComprar); acciones.add(btnVaciar); acciones.add(btnAgregarJuego); acciones.add(btnEditarStock); acciones.add(btnEliminarJuego);
        derecha.add(acciones, BorderLayout.SOUTH);
        add(derecha, BorderLayout.EAST);

        btnComprar.addActionListener(e -> comprar());
        btnVaciar.addActionListener(e -> { carrito = new ListaEnlazadaSimple(); refrescarCarrito(); });
        btnAgregarJuego.addActionListener(e -> agregarJuego());
        btnEditarStock.addActionListener(e -> editarStock());
        btnEliminarJuego.addActionListener(e -> eliminarJuego());
    }

    public void refrescarTodo() {
        cargarCatalogo();
        refrescarCarrito();
    }

    private void cargarCatalogo() {
        panelCatalogo.removeAll();
        ListaEnlazadaSimple juegos = gestorJuego.getJuegos();
        for (int i = 0; i < juegos.getTamanio(); i++) {
            final Juego j = (Juego) juegos.obtener(i);
            JPanel card = new JPanel(new BorderLayout(4, 4));
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.setBackground(Color.WHITE);
            JLabel titulo = new JLabel("  " + j.getCodigo() + " - " + j.getNombre());
            titulo.setFont(new Font("Arial", Font.BOLD, 13));
            JTextArea info = new JTextArea(j.getGenero() + " | " + j.getPlataforma() + "\nPrecio: Q" + j.getPrecio() + " | Stock: " + j.getStock() + "\n" + j.getDescripcion());
            info.setEditable(false); info.setLineWrap(true); info.setWrapStyleWord(true); info.setBackground(Color.WHITE);
            JButton btnAgregar = new JButton("Agregar al carrito");
            btnAgregar.addActionListener(e -> agregarAlCarrito(j));
            card.add(titulo, BorderLayout.NORTH); card.add(info, BorderLayout.CENTER); card.add(btnAgregar, BorderLayout.SOUTH);
            panelCatalogo.add(card);
        }
        panelCatalogo.revalidate();
        panelCatalogo.repaint();
    }

    private void agregarAlCarrito(Juego juego) {
        if (juego.getStock() <= 0) { JOptionPane.showMessageDialog(this, "Sin stock disponible."); return; }
        String cantTexto = JOptionPane.showInputDialog(this, "Cantidad:", "1");
        if (cantTexto == null) return;
        try {
            int cant = Integer.parseInt(cantTexto);
            if (cant <= 0 || cant > juego.getStock()) { JOptionPane.showMessageDialog(this, "Cantidad invalida o mayor al stock."); return; }
            for (int i = 0; i < carrito.getTamanio(); i++) {
                ItemCarrito item = (ItemCarrito) carrito.obtener(i);
                if (item.getJuego().getCodigo().equals(juego.getCodigo())) {
                    if (item.getCantidad() + cant > juego.getStock()) { JOptionPane.showMessageDialog(this, "No hay suficiente stock."); return; }
                    item.sumarCantidad(cant); refrescarCarrito(); return;
                }
            }
            carrito.agregar(new ItemCarrito(juego, cant));
            refrescarCarrito();
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Ingrese un numero valido."); }
    }

    private void refrescarCarrito() {
        modeloCarrito.setRowCount(0);
        double total = 0;
        for (int i = 0; i < carrito.getTamanio(); i++) {
            ItemCarrito item = (ItemCarrito) carrito.obtener(i);
            total += item.getSubtotal();
            modeloCarrito.addRow(new Object[]{item.getJuego().getCodigo(), item.getJuego().getNombre(), item.getCantidad(), "Q" + item.getSubtotal()});
        }
        lblTotal.setText(String.format("Total: Q%.2f", total));
    }

    private void comprar() {
        if (carrito.estaVacia()) { JOptionPane.showMessageDialog(this, "El carrito esta vacio."); return; }
        double total = 0; int cantidadJuegos = 0;
        String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        for (int i = 0; i < carrito.getTamanio(); i++) {
            ItemCarrito item = (ItemCarrito) carrito.obtener(i);
            Juego juego = item.getJuego();
            juego.setStock(juego.getStock() - item.getCantidad());
            total += item.getSubtotal(); cantidadJuegos += item.getCantidad();
            gestorVentas.agregar(new Venta(jugador.getNombre(), juego.getCodigo(), juego.getNombre(), item.getCantidad(), item.getSubtotal(), fecha));
        }
        jugador.sumarGastado(total);
        jugador.sumarXp((int)(total / 5) + (cantidadJuegos * 20));
        gestorLogros.revisarCompra(jugador, cantidadJuegos, total);
        gestorJuego.guardar();
        carrito = new ListaEnlazadaSimple();
        JOptionPane.showMessageDialog(this, "Compra realizada. XP actualizado.");
        refrescarTodo(); principal.actualizarEstado();
    }

    private void agregarJuego() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del juego:"); if (nombre == null || nombre.trim().length() == 0) return;
        String genero = JOptionPane.showInputDialog(this, "Genero:"); if (genero == null) return;
        String plataforma = JOptionPane.showInputDialog(this, "Plataforma:"); if (plataforma == null) return;
        try {
            double precio = Double.parseDouble(JOptionPane.showInputDialog(this, "Precio:"));
            int stock = Integer.parseInt(JOptionPane.showInputDialog(this, "Stock:"));
            String codigo = gestorJuego.generarNuevoCodigo();
            gestorJuego.agregarJuego(new Juego(codigo, nombre, genero, plataforma, precio, stock, "Agregado manualmente."));
            refrescarTodo();
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Datos invalidos."); }
    }

    private void editarStock() {
        String codigo = JOptionPane.showInputDialog(this, "Codigo del juego:"); if (codigo == null) return;
        Juego j = gestorJuego.buscarPorCodigo(codigo);
        if (j == null) { JOptionPane.showMessageDialog(this, "Juego no encontrado."); return; }
        try {
            int nuevo = Integer.parseInt(JOptionPane.showInputDialog(this, "Nuevo stock:", j.getStock()));
            j.setStock(nuevo); gestorJuego.guardar(); refrescarTodo();
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Numero invalido."); }
    }

    private void eliminarJuego() {
        String codigo = JOptionPane.showInputDialog(this, "Codigo del juego a eliminar:"); if (codigo == null) return;
        for (int i = 0; i < gestorJuego.getJuegos().getTamanio(); i++) {
            Juego j = (Juego) gestorJuego.getJuegos().obtener(i);
            if (j.getCodigo().equalsIgnoreCase(codigo)) {
                gestorJuego.getJuegos().eliminar(i); gestorJuego.guardar(); refrescarTodo(); return;
            }
        }
        JOptionPane.showMessageDialog(this, "Juego no encontrado.");
    }
}
