package ioc.m13.clubpadelapp.data.api;

import android.content.Context;

import ioc.m13.clubpadelapp.util.Constantes;
import ioc.m13.clubpadelapp.util.TokenManager;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiClient
 * ------------------------------------------------------
 * Clase que configura y construye el cliente Retrofit.
 *
 * Retrofit es la librería que convierte las llamadas a métodos
 * de la interfaz ApiService en peticiones HTTP reales.
 *
 */
public class ApiClient {

    /** Instancia única de ApiService (patrón Singleton). */
    private static ApiService service = null;

    /**
     * Devuelve el servicio de la API. Si aún no se ha creado,
     * lo construye con la configuración completa.
     *
     * @param ctx Contexto necesario para acceder a SharedPreferences
     *            a través de TokenManager.
     */
    public static ApiService getService(Context ctx) {

        if (service == null) {

            // Creamos el TokenManager para poder leer el JWT guardado.
            TokenManager tm = new TokenManager(ctx);

            // Interceptor de logs: imprime en Logcat el cuerpo
            // de las peticiones y respuestas. Muy útil en desarrollo.
            // En producción se debería bajar el nivel o quitarlo.
            HttpLoggingInterceptor log = new HttpLoggingInterceptor();
            log.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Cliente OkHttp: es el motor HTTP que usa Retrofit.
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(tm))
                    .addInterceptor(log)
                    .build();

            // Construimos Retrofit con:
            //      - baseUrl: URL base del backend.
            //      - client:  el OkHttpClient configurado.
            //      - converter: Gson para serializar/deserializar JSON.
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constantes.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            // Retrofit implementa la interfaz ApiService en tiempo
            //    de ejecución. No tienes que escribir la implementación.
            service = retrofit.create(ApiService.class);
        }

        return service;
    }
}