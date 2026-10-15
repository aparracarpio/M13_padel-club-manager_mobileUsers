package ioc.m13.clubpadelapp.data.model;
/**
 * DTO de entrada a peticion de registro
 * */
public class RegisterRequest {
    // Nombre usuario
    private String nombre;
    // Correo usuario
    private String email;
    // Contraseña usuario
    private String password;
    // Telefono usuario
    private String telefono;
    /**
     * Constructor usuario.
     * @param nombre nombre usuario
     * @param email correo usuario
     * @param password contraseña usuario
     * @param telefono telefono usuario
     * */
    public RegisterRequest(String nombre, String email, String password, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }
}
