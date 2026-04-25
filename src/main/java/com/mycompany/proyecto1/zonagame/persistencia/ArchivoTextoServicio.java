package gamezonepro.persistencia;

import gamezonepro.estructuras.ListaEnlazadaSimple;
import gamezonepro.estructuras.NodoSimple;
import gamezonepro.modelo.Compra;
import gamezonepro.modelo.Videojuego;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoTextoServicio {

    public ListaEnlazadaSimple<Videojuego> cargarCatalogo(String rutaArchivo) {
        ListaEnlazadaSimple<Videojuego> listaCatalogo = new ListaEnlazadaSimple<Videojuego>();

        try {
            BufferedReader lectorArchivo = new BufferedReader(new FileReader(rutaArchivo));
            String lineaActual = lectorArchivo.readLine();

            while (lineaActual != null) {
                Videojuego videojuegoLeido = convertirLineaAVideojuego(lineaActual);

                if (videojuegoLeido != null) {
                    listaCatalogo.agregar(videojuegoLeido);
                }

                lineaActual = lectorArchivo.readLine();
            }

            lectorArchivo.close();

        } catch (IOException error) {
            System.out.println("NO SE PUDO CARGAR EL CATALOGO: " + error.getMessage());
        }

        return listaCatalogo;
    }

    public void guardarCatalogo(String rutaArchivo, ListaEnlazadaSimple<Videojuego> listaCatalogo) {
        try {
            BufferedWriter escritorArchivo = new BufferedWriter(new FileWriter(rutaArchivo));
            NodoSimple<Videojuego> nodoActual = listaCatalogo.getCabeza();

            while (nodoActual != null) {
                escritorArchivo.write(nodoActual.getDato().convertirLineaTexto());
                escritorArchivo.newLine();

                nodoActual = nodoActual.getSiguiente();
            }

            escritorArchivo.close();

        } catch (IOException error) {
            System.out.println("NO SE PUDO GUARDAR EL CATALOGO: " + error.getMessage());
        }
    }

    private Videojuego convertirLineaAVideojuego(String lineaTexto) {
        try {
            String[] partesLinea = lineaTexto.split("\|");

            if (partesLinea.length != 6) {
                return null;
            }

            String codigo = partesLinea[0];
            String titulo = partesLinea[1];
            String genero = partesLinea[2];
            String plataforma = partesLinea[3];
            double precio = Double.parseDouble(partesLinea[4]);
            int stock = Integer.parseInt(partesLinea[5]);

            return new Videojuego(codigo, titulo, genero, plataforma, precio, stock);

        } catch (Exception error) {
            return null;
        }
    }
}
