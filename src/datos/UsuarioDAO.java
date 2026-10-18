
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Usuario;
import modelo.Cliente;
import modelo.Tecnico;
import modelo.Administrador;

public class UsuarioDAO {

    public Usuario iniciarSesion(String email, String contrasena) {

        String sql = """
                SELECT id_usuario, nombre, email, rol
                FROM usuario
                WHERE email = ? AND contrasena = ?
                """;

        try (
            Connection conexion = ConexionBD.conectar();
            PreparedStatement sentencia = conexion.prepareStatement(sql)
        ) {

            sentencia.setString(1, email);
            sentencia.setString(2, contrasena);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    int id = resultado.getInt("id_usuario");
                    String nombre = resultado.getString("nombre");
                    String correo = resultado.getString("email");
                    String rol = resultado.getString("rol");

                    switch (rol) {

                        case "CLIENTE":
                            return new Cliente(id, nombre, correo);

                        case "TECNICO":
                            return new Tecnico(id, nombre, correo);

                        case "ADMINISTRADOR":
                            return new Administrador(id, nombre, correo);

                        default:
                            System.out.println("Rol desconocido: " + rol);
                            return null;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al iniciar sesion:");
            System.out.println(e.getMessage());
        }

        return null;
    }
}
