package ioc.m13.clubpadelapp.data.model;

public class RegisterRequest {
    private String nombre;
    private String email;
    private String password;
    private String telefono;

    public RegisterRequest(String nombre, String email, String password, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }
}
