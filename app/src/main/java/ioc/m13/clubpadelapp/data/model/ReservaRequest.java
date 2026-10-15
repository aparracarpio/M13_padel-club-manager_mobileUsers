package ioc.m13.clubpadelapp.data.model;
/**
 * DTO entrada para Reservas
 * */
public class ReservaRequest {
    // Identificador pista
    private Long idPista;
    // Fecha con formato yyyy-MM-dd
    private String fecha;
    // Hora inicio HH:MM
    private String horaInicio;
    // Hora fin HH:MM
    private String horaFin;
    /**
    * Constructor
     * @param idPista identificador pista
     * @param fecha fecha reserva pista
     * @param horaInicio horia inicio reserva
     * @param horaFin horia fin reserva
    * */
    public ReservaRequest(Long idPista, String fecha, String horaInicio, String horaFin) {
        this.idPista = idPista;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
}
