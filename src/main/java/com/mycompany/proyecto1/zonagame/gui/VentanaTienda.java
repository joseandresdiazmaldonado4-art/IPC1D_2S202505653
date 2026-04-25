package gamezonepro.gui;

import javax.swing.*;
import java.awt.*;

public class VentanaTienda extends JFrame {
    public VentanaTienda() {
        setTitle("GameZone - Tienda de Videojuegos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Bienvenido a la Tienda GameZone", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(label, BorderLayout.CENTER);

        add(panel);
    }
}
