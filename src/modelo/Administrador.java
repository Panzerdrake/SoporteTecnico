
package modelo;

public class Administrador extends Usuario {

    public Administrador(int idUsuario, String nombre, String email) {
        super(idUsuario, nombre, email, "ADMINISTRADOR");
    }
    @Override
    public String obtenerTipoUsuario() {
        return "ADMINISTRADOR";
    }
}
