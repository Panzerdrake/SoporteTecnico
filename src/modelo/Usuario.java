
package modelo;

public abstract class Usuario {

    private int idUsuario;
    private String nombre;
    private String email;
    private String rol;

    public Usuario(int idUsuario, String nombre, String email, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }

    public abstract String obtenerTipoUsuario();
}
