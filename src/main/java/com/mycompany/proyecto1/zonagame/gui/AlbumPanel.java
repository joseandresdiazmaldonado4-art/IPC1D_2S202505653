package gamezonepro.gui;

import gamezonepro.estructuras.NodoMatriz;
import javax.swing.*;
import java.awt.*;

public class AlbumPanel extends JPanel {

    private NodoMatriz<String> primerNodo;

    public AlbumPanel(NodoMatriz<String> primerNodo) {
        this.primerNodo = primerNodo;
        setLayout(new GridLayout(5, 5)); // 5x5 grid para el álbum
        crearAlbum();
    }

    private void crearAlbum() {
        NodoMatriz<String> nodoActual = primerNodo;

        while (nodoActual != null) {
            JButton botonCarta = new JButton(nodoActual.getDato());
            add(botonCarta);
            nodoActual = nodoActual.getDerecha();
        }
    }
}