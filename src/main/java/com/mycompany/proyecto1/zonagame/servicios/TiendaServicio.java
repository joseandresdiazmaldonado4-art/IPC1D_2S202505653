package gamezonepro.servicios;

import gamezonepro.estructuras.CriterioBusqueda;
import gamezonepro.estructuras.ListaEnlazadaSimple;
import gamezonepro.modelo.Compra;
import gamezonepro.modelo.ProductoCarrito;
import gamezonepro.modelo.Videojuego;
import gamezonepro.persistencia.ArchivoTextoServicio;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TiendaServicio {

    private final String rutaCatalogo;
    private final String rutaCompras;

    private ListaEnlazadaSimple<Videojuego> catalogoVideojuegos;
    private ListaEnlazadaSimple<ProductoCarrito> carritoCompras;
    private ListaEnlazadaSimple<Compra> historialCompras;

    private ArchivoTextoServicio archivoTextoServicio;

    public TiendaServicio(String rutaCatalogo, String rutaCompras) {
        this.rutaCatalogo = rutaCatalogo;
        this.rutaCompras = rutaCompras;

        archivoTextoServicio = new ArchivoTextoServicio();
        catalogoVideojuegos = archivoTextoServicio.cargarCatalogo(rutaCatalogo);
        carritoCompras = new ListaEnlazadaSimple<ProductoCarrito>();
        historialCompras = archivoTextoServicio.cargarCompras(rutaCompras);
    }
    
    public ListaEnlazadaSimple<Videojuego> getCatalogoVideojuegos() {
        return catalogoVideojuegos;
    }
    
    public ListaEnlazadaSimple<ProductoCarrito> getCarritoCompras() {
        return carritoCompras;
    }
    
    public ListaEnlazadaSimple<Compra> getHistorialCompras() {
        return historialCompras;
    }
}