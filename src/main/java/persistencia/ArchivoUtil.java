package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoUtil {
    public static void asegurarCarpetas() {
        new File("data").mkdirs();
        new File("reportes").mkdirs();
    }

    public static BufferedReader abrirLectura(String ruta) throws IOException {
        return new BufferedReader(new FileReader(ruta));
    }

    public static BufferedWriter abrirEscritura(String ruta) throws IOException {
        File archivo = new File(ruta);
        File padre = archivo.getParentFile();
        if (padre != null) padre.mkdirs();
        return new BufferedWriter(new FileWriter(archivo));
    }
}
