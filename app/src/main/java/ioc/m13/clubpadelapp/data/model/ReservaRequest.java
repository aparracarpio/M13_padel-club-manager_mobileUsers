package ioc.m13.clubpadelapp.data.model;

public class ReservaRequest {
    private Long idPista;
    private String fecha;
    private String horaInicio;
    private String horaFin;

    public ReservaRequest(Long idPista, String fecha, String horaInicio, String horaFin) {
        this.idPista = idPista;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
}
