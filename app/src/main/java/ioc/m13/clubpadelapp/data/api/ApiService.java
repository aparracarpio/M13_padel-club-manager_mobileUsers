package ioc.m13.clubpadelapp.data.api;

import java.util.List;

import ioc.m13.clubpadelapp.data.model.LoginRequest;
import ioc.m13.clubpadelapp.data.model.LoginResponse;
import ioc.m13.clubpadelapp.data.model.Pista;
import ioc.m13.clubpadelapp.data.model.RegisterRequest;
import ioc.m13.clubpadelapp.data.model.ReservaRequest;
import ioc.m13.clubpadelapp.data.model.ReservaResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Interfaz que define la API REST del backend ClubPadel
 * */
public interface ApiService {

    // ------------------------------------------------------
    // AUTENTICACIÓN
    // ------------------------------------------------------

    /**
     * POST /api/auth/login
     * Envía email y contraseña. Devuelve un token JWT,
     * el rol y el nombre del usuario.
     */
    @POST("api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    /**
     * POST /api/auth/register
     * Registra un usuario nuevo. No devuelve cuerpo (Void)
     * porque solo nos interesa el código HTTP (200 o 400).
     */
    @POST("api/auth/register")
    Call<Void> register(@Body RegisterRequest request);

    // ------------------------------------------------------
    // PISTAS
    // ------------------------------------------------------

    /**
     * GET /api/pistas
     * Devuelve la lista completa de pistas del club.
     */
    @GET("api/pistas")
    Call<List<Pista>> getPistas();

    /**
     * GET /api/pistas/disponibles
     * Devuelve solo las pistas que están en estado "Disponible".
     */
    @GET("api/pistas/disponibles")
    Call<List<Pista>> getPistasDisponibles();

    // ------------------------------------------------------
    // RESERVAS
    // ------------------------------------------------------

    /**
     * POST /api/reservas
     * Crea una reserva. El backend obtiene el usuario a partir
     * del token JWT (lo añade el AuthInterceptor automáticamente).
     * Solo hay que enviar idPista, fecha y horas.
     */
    @POST("api/reservas")
    Call<ReservaResponse> crearReserva(@Body ReservaRequest request);

    /**
     * GET /api/reservas/mis-reservas
     * Devuelve las reservas del usuario autenticado.
     */
    @GET("api/reservas/mis-reservas")
    Call<List<ReservaResponse>> getMisReservas();

    /**
     * DELETE /api/reservas/{id}
     * Cancela (o marca como "Cancelada") una reserva concreta.
     * El {id} se sustituye por el valor del parámetro anotado
     * con @Path.
     */
    @DELETE("api/reservas/{id}")
    Call<Void> cancelarReserva(@Path("id") Long id);
}