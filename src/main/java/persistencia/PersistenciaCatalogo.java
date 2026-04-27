package persistencia;

import estructuras.ListaEnlazadaSimple;
import modelo.Juego;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

public class PersistenciaCatalogo {
    private static final String RUTA = "data/catalogo.txt";

    public ListaEnlazadaSimple cargarCatalogo() {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
        try {
            File archivo = new File(RUTA);
            if (!archivo.exists()) crearCatalogoBase();
            BufferedReader br = ArchivoUtil.abrirLectura(RUTA);
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().length() == 0) continue;
                String[] p = linea.split("\\|");
                if (p.length >= 6) {
                    String codigo = p[0].trim();
                    String nombre = p[1].trim();
                    String genero = p[2].trim();
                    String plataforma;
                    double precio;
                    int stock;
                    String descripcion = "Juego disponible en catalogo.";
                    if (esNumeroDecimal(p[3].trim())) {
                        precio = Double.parseDouble(p[3].trim());
                        plataforma = p[4].trim();
                        stock = Integer.parseInt(p[5].trim());
                        if (p.length >= 7) descripcion = p[6].trim();
                    } else {
                        plataforma = p[3].trim();
                        precio = Double.parseDouble(p[4].trim());
                        stock = Integer.parseInt(p[5].trim());
                        if (p.length >= 7) descripcion = p[6].trim();
                    }
                    lista.agregar(new Juego(codigo, nombre, genero, plataforma, precio, stock, descripcion));
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error cargando catalogo: " + e.getMessage());
        }
        return lista;
    }

    private boolean esNumeroDecimal(String texto) {
        try { Double.parseDouble(texto); return true; } catch (Exception e) { return false; }
    }

    public void guardarCatalogo(ListaEnlazadaSimple juegos) {
        try {
            BufferedWriter bw = ArchivoUtil.abrirEscritura(RUTA);
            for (int i = 0; i < juegos.getTamanio(); i++) {
                Juego j = (Juego) juegos.obtener(i);
                bw.write(j.toArchivo());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Error guardando catalogo: " + e.getMessage());
        }
    }

    private void crearCatalogoBase() throws Exception {
        BufferedWriter bw = ArchivoUtil.abrirEscritura(RUTA);
        bw.write("J001|The Legend of Zelda Twilight Princess|Aventura|Nintendo Wii|599.99|10|Aventura clasica de fantasia."); bw.newLine();
        bw.write("J002|Mortal Kombat|Pelea|PlayStation|399.99|5|Juego de combate."); bw.newLine();
        bw.write("J003|Super Smash Bros Brawl|Pelea|Nintendo Wii|249.50|8|Peleas multijugador."); bw.newLine();
        bw.write("J004|Mario Kart World|Carreras|Nintendo Switch|199.99|12|Carreras arcade."); bw.newLine();
        bw.write("J005|Super Mario 64|Aventura|Nintendo 64|349.99|6|Plataformas 3D."); bw.newLine();
        bw.write("J006|five nights at freddy's|Terror|PC|279.99|4|Terror de supervivencia."); bw.newLine();
        bw.write("J007|Super Mario Galaxy|Aventura|Nintendo Wii|329.99|7|Aventura espacial."); bw.newLine();
        bw.write("J008|Halo: Reach|Pelea|Xbox|189.99|9|Accion futurista."); bw.newLine();
        bw.write("J009|Grand Theft Auto V|Accion|Multiplataforma|549.99|15|Mundo abierto."); bw.newLine();
        bw.write("J010|Minecraft|Construccion|PC|499.99|20|Construccion libre."); bw.newLine();
        bw.write("J011|God of War|Accion|PlayStation|449.99|10|Aventura de accion."); bw.newLine();
        bw.write("J012|Call of Duty: Black Ops II|Disparos|Xbox|299.99|7|Disparos en primera persona."); bw.newLine();
        bw.write("J013|Resident Evil 4|Terror|Multiplataforma|399.99|6|Terror y accion."); bw.newLine();
        bw.write("J014|League of Legends|Estrategia|PC|0.00|99|Competitivo en linea."); bw.newLine();
        bw.write("J015|Pokemon Rojo Fuego|RPG|Game Boy Advance|349.99|4|RPG clasico."); bw.newLine();
        bw.write("J016|FIFA 23|Deportes|Multiplataforma|499.99|9|Futbol deportivo."); bw.newLine();
        bw.close();
    }
}
