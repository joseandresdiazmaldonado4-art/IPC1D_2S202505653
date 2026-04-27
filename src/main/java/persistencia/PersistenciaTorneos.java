package persistencia;

import estructuras.ListaEnlazadaSimple;
import modelo.Torneo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

public class PersistenciaTorneos {
    private static final String RUTA = "data/torneos.txt";

    public ListaEnlazadaSimple cargar() {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
        try {
            File archivo = new File(RUTA);
            if (!archivo.exists()) crearBase();
            BufferedReader br = ArchivoUtil.abrirLectura(RUTA);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split("\\|");
                if (p.length >= 4) {
                    Torneo t = new Torneo(p[0], p[1], p[2], Integer.parseInt(p[3]));
                    if (p.length >= 5) {
                        int vendidos = Integer.parseInt(p[4]);
                        for (int i = 0; i < vendidos; i++) t.venderUno();
                    }
                    lista.agregar(t);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error cargando torneos: " + e.getMessage());
        }
        return lista;
    }

    public void guardar(ListaEnlazadaSimple torneos) {
        try {
            BufferedWriter bw = ArchivoUtil.abrirEscritura(RUTA);
            for (int i = 0; i < torneos.getTamanio(); i++) {
                bw.write(((Torneo) torneos.obtener(i)).toArchivo());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Error guardando torneos: " + e.getMessage());
        }
    }

    private void crearBase() throws Exception {
        BufferedWriter bw = ArchivoUtil.abrirEscritura(RUTA);
        bw.write("T001|Copa Smash|Super Smash Bros Brawl|20|0"); bw.newLine();
        bw.write("T002|Noche Mortal Kombat|Mortal Kombat|15|0"); bw.newLine();
        bw.write("T003|Grand Prix Mario Kart|Mario Kart World|25|0"); bw.newLine();
        bw.close();
    }
}
