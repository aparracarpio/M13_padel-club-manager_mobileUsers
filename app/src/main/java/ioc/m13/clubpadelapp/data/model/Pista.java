package ioc.m13.clubpadelapp.data.model;
/**
 * Modelo de una pista del club
 * */
public class Pista {
    // Identificador pista
    private Long id;
    // Nombre pista
    private String nombre;
    // Tipo pista
    private String tipo;
    // Estado pista
    private String estado;

    // Getters y setters
    public Long getId() {return this.id;}
    public String getNombre() {return this.nombre;}
    public String getTipo() {return this.tipo;}
    public String getEstado() {return this.estado;}
}
