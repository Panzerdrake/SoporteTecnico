package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Producto;
import modelo.Ticket;

public class TicketDAO {

    public List<Producto> obtenerProductos() {

        List<Producto> productos = new ArrayList<>();

        String sql = """
                SELECT id_producto, nombre, modelo
                FROM producto
                ORDER BY nombre
                """;

        try (
            Connection conexion = ConexionBD.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery()
        ) {

            while (resultado.next()) {

                Producto producto = new Producto(
                        resultado.getInt("id_producto"),
                        resultado.getString("nombre"),
                        resultado.getString("modelo")
                );

                productos.add(producto);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los productos:");
            System.out.println(e.getMessage());
        }

        return productos;
    }

    public boolean registrarTicket(
            String asunto,
            String descripcion,
            int idCliente,
            int idProducto) {
    	
    	

        String sql = """
                INSERT INTO ticket
                (asunto, descripcion, id_cliente, id_producto, id_estado)
                VALUES (?, ?, ?, ?, 1)
                """;

        try (
            Connection conexion = ConexionBD.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)
        ) {

            sentencia.setString(1, asunto);
            sentencia.setString(2, descripcion);
            sentencia.setInt(3, idCliente);
            sentencia.setInt(4, idProducto);

            int filas = sentencia.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar el ticket:");
            System.out.println(e.getMessage());

            return false;
        }
    }
    
    public List<Ticket> obtenerTicketsCliente(int idCliente) {

        List<Ticket> tickets = new ArrayList<>();

        String sql = """
                SELECT
                    t.id_ticket,
                    t.asunto,
                    t.descripcion,
                    t.fecha_creacion,
                    p.nombre AS producto,
                    e.nombre AS estado
                FROM ticket t
                JOIN producto p ON t.id_producto = p.id_producto
                JOIN estado e ON t.id_estado = e.id_estado
                WHERE t.id_cliente = ?
                ORDER BY t.fecha_creacion DESC
                """;

        try (
            Connection conexion = ConexionBD.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)
        ) {

            sentencia.setInt(1, idCliente);

            try (ResultSet resultado = sentencia.executeQuery()) {

                while (resultado.next()) {

                    Ticket ticket = new Ticket(
                            resultado.getInt("id_ticket"),
                            resultado.getString("asunto"),
                            resultado.getString("descripcion"),
                            resultado.getTimestamp("fecha_creacion")
                                     .toLocalDateTime(),
                            resultado.getString("producto"),
                            resultado.getString("estado")
                    );

                    tickets.add(ticket);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar los tickets:");
            System.out.println(e.getMessage());
        }

        return tickets;
    }
}