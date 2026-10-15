package ioc.m13.clubpadelapp.data.model;

/**
 * DTO entrada para POST /api/login
 * */
public class LoginRequest {
    // Correo usuario
    private String email;
    // Contraseña en texto plano
    private String password;
    /**
     * Constructor
     * @param email correo usuario
     * @param password contraseña usuario
     * */
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
