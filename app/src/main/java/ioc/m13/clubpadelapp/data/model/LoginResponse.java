package ioc.m13.clubpadelapp.data.model;
/**
 * DTO de salida POST /api/auth/login
 * */
public class LoginResponse {
    // JWT firmado
    private String token;
    // ROL -> ADMIN o USER
    private String rol;
    // Nombre visible del usuario
    private String nombre;

    // getters y setters
    public String getToken() {return this.token;}
    public String getRol() {return this.rol;}
    public String getNombre() {return this.nombre;}
}
