package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class EstiloSwing {
    public static final Color GRIS_FONDO = new Color(238, 238, 238);
    public static final Color GRIS_PANEL = new Color(225, 225, 225);
    public static final Color GRIS_BORDE = new Color(160, 160, 160);

    public static void aplicarLook() {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) { }
    }

    public static void panel(JPanel panel) {
        panel.setBackground(GRIS_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    }

    public static void boton(JButton boton) {
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.PLAIN, 12));
    }
}
