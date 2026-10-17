package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/soporte_tecnico";

    private static final String USUARIO = System.getenv("MYSQL_USER");
    private static final String CONTRASENA = System.getenv("MYSQL_PASSWORD");

    public static Connection conectar() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(
                    URL,
                    USUARIO,
                    CONTRASENA
            );

            System.out.println("Conexion a MySQL realizada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al conectar con MySQL:");
            System.out.println(e.getMessage());
        }

        return conexion;
    }
}