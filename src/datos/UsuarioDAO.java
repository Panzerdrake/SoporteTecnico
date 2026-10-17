package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Usuario;

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
                    return new Usuario(
                        resultado.getInt("id_usuario"),
                        resultado.getString("nombre"),
                        resultado.getString("email"),
                        resultado.getString("rol")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al iniciar sesion:");
            System.out.println(e.getMessage());
        }

        return null;
    }
}