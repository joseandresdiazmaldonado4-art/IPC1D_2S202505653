package gamezonepro;

import gamezonepro.gui.VentanaTienda;
import javax.swing.SwingUtilities;

public class AppGameZonePro {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaTienda ventanaTiendaPrincipal = new VentanaTienda();
                ventanaTiendaPrincipal.setVisible(true);
            }
        });
    }
}