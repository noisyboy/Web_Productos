package mx.samedgo.productos;

import java.time.LocalDateTime;

public class Productos {

    private Integer producto_id;
    private String seccion;
    private String nombre_articulo;
    private double precio;
    private LocalDateTime fecha;
    private String importado;
    private String pais_origen;

    public Productos() {
    }

    public Productos(Integer producto_id, String seccion, String nombre_articulo, double precio,
                     LocalDateTime fecha, String importado, String pais_origen) {
        this.producto_id = producto_id;
        this.seccion = seccion;
        this.nombre_articulo = nombre_articulo;
        this.precio = precio;
        this.fecha = fecha;
        this.importado = importado;
        this.pais_origen = pais_origen;
    }

    public Productos(Integer producto_id, String seccion, String nombre_articulo, double precio,
                     String importado, String pais_origen) {
        this.producto_id = producto_id;
        this.seccion = seccion;
        this.nombre_articulo = nombre_articulo;
        this.precio = precio;
        this.importado = importado;
        this.pais_origen = pais_origen;
    }

    public Productos(String seccion, String nombre_articulo, double precio, String importado, String pais_origen) {
        this.seccion = seccion;
        this.nombre_articulo = nombre_articulo;
        this.precio = precio;
        this.importado = importado;
        this.pais_origen = pais_origen;
    }

    public Integer getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Integer producto_id) {
        this.producto_id = producto_id;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getNombre_articulo() {
        return nombre_articulo;
    }

    public void setNombre_articulo(String nombre_articulo) {
        this.nombre_articulo = nombre_articulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getImportado() {
        return importado;
    }

    public void setImportado(String importado) {
        this.importado = importado;
    }

    public String getPais_origen() {
        return pais_origen;
    }

    public void setPais_origen(String pais_origen) {
        this.pais_origen = pais_origen;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "producto_id=" + producto_id +
                ", seccion='" + seccion + '\'' +
                ", nombre_articulo='" + nombre_articulo + '\'' +
                ", precio=" + precio +
                ", fecha=" + fecha +
                ", importado='" + importado + '\'' +
                ", pais_origen='" + pais_origen + '\'' +
                '}';
    }
}