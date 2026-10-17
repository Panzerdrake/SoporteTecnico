package modelo;

import java.time.LocalDateTime;

public class Ticket {

    private int idTicket;
    private String asunto;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private String producto;
    private String estado;

    public Ticket(int idTicket, String asunto, String descripcion,
                  LocalDateTime fechaCreacion, String producto, String estado) {

        this.idTicket = idTicket;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.producto = producto;
        this.estado = estado;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public String getProducto() {
        return producto;
    }

    public String getEstado() {
        return estado;
    }
}