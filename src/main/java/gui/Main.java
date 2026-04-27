package gui;

import javax.swing.SwingUtilities;
import persistencia.ArchivoUtil;

public class Main {
    public static void main(String[] args) {
        ArchivoUtil.asegurarCarpetas();
        EstiloSwing.aplicarLook();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SeleccionPerfilFrame().setVisible(true);
            }
        });
    }
}
