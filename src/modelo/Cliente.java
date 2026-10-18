
package modelo;

public class Cliente extends Usuario {

    public Cliente(int idUsuario, String nombre, String email) {
        super(idUsuario, nombre, email, "CLIENTE");
    }
    @Override
    public String obtenerTipoUsuario() {
        return "CLIENTE";
    }
}
