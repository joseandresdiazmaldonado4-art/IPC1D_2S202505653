package persistencia;

import estructuras.MatrizOrtogonal;
import modelo.Carta;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

public class PersistenciaAlbum {
    private static final String RUTA = "data/album.txt";

    public void cargar(MatrizOrtogonal matriz) {
        try {
            File archivo = new File(RUTA);
            if (!archivo.exists()) return;
            BufferedReader br = ArchivoUtil.abrirLectura(RUTA);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split("\\|");
                if (p.length >= 5) {
                    int fila = Integer.parseInt(p[0]);
                    int col = Integer.parseInt(p[1]);
                    matriz.colocarDato(fila, col, new Carta(p[2], p[3], p[4]));
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error cargando album: " + e.getMessage());
        }
    }

    public void guardar(MatrizOrtogonal matriz) {
        try {
            BufferedWriter bw = ArchivoUtil.abrirEscritura(RUTA);
            for (int f = 0; f < matriz.getFilas(); f++) {
                for (int c = 0; c < matriz.getColumnas(); c++) {
                    Object dato = matriz.obtenerDato(f, c);
                    if (dato instanceof Carta) {
                        bw.write(((Carta) dato).toArchivo(f, c));
                        bw.newLine();
                    }
                }
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Error guardando album: " + e.getMessage());
        }
    }
}
