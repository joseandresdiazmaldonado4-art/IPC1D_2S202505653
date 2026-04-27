package logica;

import estructuras.ListaEnlazadaSimple;
import estructuras.MatrizOrtogonal;
import modelo.Carta;
import modelo.Juego;
import modelo.Torneo;
import modelo.Venta;
import persistencia.ArchivoUtil;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReporteHTML {
    private String nombreArchivo(String tipo) {
        String fecha = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
        return "reportes/" + fecha + "_" + tipo + ".html";
    }

    private void escribirInicio(BufferedWriter bw, String titulo) throws Exception {
        bw.write("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>" + titulo + "</title>");
        bw.write("<style>body{font-family:Arial;background:#f4f4f4;color:#222;}h1{background:#555;color:white;padding:12px;}table{border-collapse:collapse;width:100%;background:white;}th{background:#777;color:white;}td,th{border:1px solid #bbb;padding:8px;text-align:left;}tr:nth-child(even){background:#eee;}</style>");
        bw.write("</head><body><h1>" + titulo + "</h1>");
    }

    private void escribirFin(BufferedWriter bw) throws Exception { bw.write("</body></html>"); }

    public String inventario(ListaEnlazadaSimple juegos) {
        String ruta = nombreArchivo("Inventario");
        try {
            BufferedWriter bw = ArchivoUtil.abrirEscritura(ruta);
            escribirInicio(bw, "Reporte de Inventario");
            bw.write("<table><tr><th>Codigo</th><th>Nombre</th><th>Genero</th><th>Plataforma</th><th>Precio</th><th>Stock</th></tr>");
            for (int i = 0; i < juegos.getTamanio(); i++) {
                Juego j = (Juego) juegos.obtener(i);
                bw.write("<tr><td>" + j.getCodigo() + "</td><td>" + j.getNombre() + "</td><td>" + j.getGenero() + "</td><td>" + j.getPlataforma() + "</td><td>Q" + j.getPrecio() + "</td><td>" + j.getStock() + "</td></tr>");
            }
            bw.write("</table>"); escribirFin(bw); bw.close();
        } catch (Exception e) { return "Error: " + e.getMessage(); }
        return ruta;
    }

    public String ventas(ListaEnlazadaSimple ventas) {
        String ruta = nombreArchivo("Ventas");
        try {
            BufferedWriter bw = ArchivoUtil.abrirEscritura(ruta);
            escribirInicio(bw, "Reporte de Ventas");
            bw.write("<table><tr><th>Fecha</th><th>Jugador</th><th>Codigo</th><th>Juego</th><th>Cantidad</th><th>Total</th></tr>");
            for (int i = 0; i < ventas.getTamanio(); i++) {
                Venta v = (Venta) ventas.obtener(i);
                bw.write("<tr><td>" + v.getFecha() + "</td><td>" + v.getJugador() + "</td><td>" + v.getCodigoJuego() + "</td><td>" + v.getNombreJuego() + "</td><td>" + v.getCantidad() + "</td><td>Q" + v.getTotal() + "</td></tr>");
            }
            bw.write("</table>"); escribirFin(bw); bw.close();
        } catch (Exception e) { return "Error: " + e.getMessage(); }
        return ruta;
    }

    public String album(MatrizOrtogonal matriz) {
        String ruta = nombreArchivo("Album");
        try {
            BufferedWriter bw = ArchivoUtil.abrirEscritura(ruta);
            escribirInicio(bw, "Reporte de Album");
            bw.write("<table><tr><th>Fila</th><th>Columna</th><th>Carta</th><th>Rareza</th></tr>");
            for (int f = 0; f < matriz.getFilas(); f++) {
                for (int c = 0; c < matriz.getColumnas(); c++) {
                    Object dato = matriz.obtenerDato(f, c);
                    if (dato instanceof Carta) {
                        Carta carta = (Carta) dato;
                        bw.write("<tr><td>" + (f + 1) + "</td><td>" + (c + 1) + "</td><td>" + carta.getNombre() + "</td><td>" + carta.getRareza() + "</td></tr>");
                    }
                }
            }
            bw.write("</table>"); escribirFin(bw); bw.close();
        } catch (Exception e) { return "Error: " + e.getMessage(); }
        return ruta;
    }

    public String torneos(ListaEnlazadaSimple torneos) {
        String ruta = nombreArchivo("Torneos");
        try {
            BufferedWriter bw = ArchivoUtil.abrirEscritura(ruta);
            escribirInicio(bw, "Reporte de Torneos");
            bw.write("<table><tr><th>Codigo</th><th>Nombre</th><th>Juego</th><th>Tickets Totales</th><th>Vendidos</th><th>Restantes</th></tr>");
            for (int i = 0; i < torneos.getTamanio(); i++) {
                Torneo t = (Torneo) torneos.obtener(i);
                bw.write("<tr><td>" + t.getCodigo() + "</td><td>" + t.getNombre() + "</td><td>" + t.getJuego() + "</td><td>" + t.getTicketsDisponibles() + "</td><td>" + t.getTicketsVendidos() + "</td><td>" + t.getTicketsRestantes() + "</td></tr>");
            }
            bw.write("</table>"); escribirFin(bw); bw.close();
        } catch (Exception e) { return "Error: " + e.getMessage(); }
        return ruta;
    }
}
