
package modelo;

public class Tecnico extends Usuario {

    public Tecnico(int idUsuario, String nombre, String email) {
        super(idUsuario, nombre, email, "TECNICO");
    }
    @Override
    public String obtenerTipoUsuario() {
        return "TECNICO";
    }
}
