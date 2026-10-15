package ioc.m13.clubpadelapp.data.model;
/**
 * DTO de salida para los datos de una reserva
 * */
public class ReservaResponse {
    // Identificador reserva
    private Long id;
    // Identificador pista
    private Long idPista;
    // Nombre pista
    private String nombrePista;
    // Fecha con formato yyyy-MM-dd
    private String fecha;
    // hora inicio HH:MM
    private String horaInicio;
    // hora fin HH:MM
    private String horaFin;
    // Estado reserva
    private String estado;

    /*Getters*/

    public Long getId() {return id;}
    public Long getIdPista() {return idPista;}
    public String getNombrePista() {return nombrePista;}
    public String getFecha() {return fecha;}
    public String getHoraInicio() {return horaInicio;}
    public String getHoraFin() {return horaFin;}
    public String getEstado() {return estado;}
}