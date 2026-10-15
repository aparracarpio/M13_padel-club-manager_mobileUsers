package ioc.m13.clubpadelapp.data.api;

import java.io.IOException;

import ioc.m13.clubpadelapp.util.TokenManager;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    /** Gestor de sesión: de aquí sacamos el token guardado. */
    private final TokenManager tokenManager;

    /**
     * Constructor: recibe el TokenManager que se le pasa desde ApiClient.
     */
    public AuthInterceptor(TokenManager tm) {
        this.tokenManager = tm;
    }

    /**
     * Metodo principal del interceptor. OkHttp lo llama cada vez
     * que va a enviar una petición.
     *
     * @param chain Cadena de interceptores. Con chain.proceed(request)
     *              se pasa la petición al siguiente interceptor.
     */
    @Override
    public Response intercept(Chain chain) throws IOException {

        // Guardamos la petición original
        Request original = chain.request();

        // Leemos el token guardado en SharedPreferences
        String token = tokenManager.getToken();

        /* Si no hay token (usuario no logueado), dejamos pasar la petición sin modificarla. El backend devolverá 401
          si el endpoint era protegido.*/
        if (token == null) return chain.proceed(original);

        // Si hay token, creamos una copia de la petición añadiéndole el header Authorization.
        Request request = original.newBuilder()
                .header("Authorization", "Bearer " + token)
                .build();

        // Continuamos con la petición modificada.
        return chain.proceed(request);
    }
}