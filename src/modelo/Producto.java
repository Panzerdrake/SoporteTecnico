package modelo;

public class Producto {

    private int idProducto;
    private String nombre;
    private String modelo;

    public Producto(int idProducto, String nombre, String modelo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.modelo = modelo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getModelo() {
        return modelo;
    }

    @Override
    public String toString() {
        return nombre + " - " + modelo;
    }
}