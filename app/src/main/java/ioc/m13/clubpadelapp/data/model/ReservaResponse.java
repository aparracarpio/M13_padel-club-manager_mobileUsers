package ioc.m13.clubpadelapp.data.model;

public class ReservaResponse {
    private Long id;
    private Long idPista;
    private String nombrePista;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private String estado;

    public Long getId()             { return id; }
    public Long getIdPista()        { return idPista; }
    public String getNombrePista()  { return nombrePista; }
    public String getFecha()        { return fecha; }
    public String getHoraInicio()   { return horaInicio; }
    public String getHoraFin()      { return horaFin; }
    public String getEstado()       { return estado; }
}