package ioc.m13.clubpadelapp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * TokenManager
 * ------------------------------------------------------
 * Gestor de la sesión del usuario en la app Android.
 *
 * Se encarga de:
 *   1. Guardar el token JWT, el rol y el nombre del usuario
 *      cuando se hace login correctamente.
 *   2. Leer esos datos cuando se necesitan (por ejemplo, para
 *      añadir el token a las peticiones HTTP).
 *   3. Comprobar si hay sesión activa (isLoggedIn).
 *   4. Borrar la sesión cuando el usuario cierra sesión.
 *
 */
public class TokenManager {

    // ----------------------------------------------------------
    // CONSTANTES
    // ----------------------------------------------------------

    /** Nombre del archivo XML donde se guardan los datos.*/
    private static final String PREF = "padel_prefs";
    /** Clave con la que se guarda el token JWT. */
    private static final String KEY_TOKEN = "jwt";
    /** Clave con la que se guarda el rol del usuario ("ADMIN" o "USER"). */
    private static final String KEY_ROL = "rol";

    /** Clave con la que se guarda el nombre del usuario. */
    private static final String KEY_NOMBRE = "nombre";

    // ----------------------------------------------------------
    // ATRIBUTOS
    // ----------------------------------------------------------

    /**
     * Referencia al archivo de preferencias.
     * Se asigna una sola vez en el constructor, por eso es final.
     */
    private final SharedPreferences prefs;

    // ----------------------------------------------------------
    // CONSTRUCTOR
    // ----------------------------------------------------------

    /**
     * Crea un TokenManager asociado al contexto de la app.
     *
     * @param ctx Contexto desde donde se instancia (una Activity,
     *            el Application, etc.). Necesario porque Android
     *            necesita saber a que app pertenecen las preferencias.
     *
     */
    public TokenManager(Context ctx) {
        prefs = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    // ----------------------------------------------------------
    // GUARDAR SESIÓN
    // ----------------------------------------------------------

    /**
     * Guarda los datos del usuario tras un login correcto.
     * Se llama desde LoginActivity cuando la petición a /api/auth/login
     * devuelve un token válido.
     *
     * @param token  JWT devuelto por el backend.
     * @param rol    "ADMIN" o "USER".
     * @param nombre Nombre visible del usuario.
     *
     * .edit()      → abre un editor para modificar las preferencias.
     * .putString() → escribe un valor String asociado a una clave.
     * .apply()     → confirma los cambios de forma asíncrona
     *                (no bloquea el hilo principal).
     */
    public void saveSession(String token, String rol, String nombre) {
        prefs.edit()
                .putString(KEY_TOKEN, token)
                .putString(KEY_ROL, rol)
                .putString(KEY_NOMBRE, nombre)
                .apply();
    }

    // ----------------------------------------------------------
    // LEER DATOS
    // ----------------------------------------------------------

    /**
     * Devuelve el token JWT guardado, o null si no hay sesión.
     */
    public String getToken() {
        return prefs.getString(KEY_TOKEN, null);
    }

    /**
     * Devuelve el rol del usuario ("ADMIN" o "USER"),
     * o null si no hay sesión.
     */
    public String getRol() {
        return prefs.getString(KEY_ROL, null);
    }

    /**
     * Devuelve el nombre del usuario,
     * o null si no hay sesión.
     */
    public String getNombre() {
        return prefs.getString(KEY_NOMBRE, null);
    }

    // ----------------------------------------------------------
    // COMPROBAR SESIÓN
    // ----------------------------------------------------------

    /**
     * Indica si hay una sesión activa.
     * Se usa en MainActivity al arrancar la app:
     *   - true  → redirige a PistasActivity (ya está logueado).
     *   - false → redirige a LoginActivity (debe iniciar sesión).
     *
     */
    public boolean isLoggedIn() {
        return getToken() != null;
    }

    // ----------------------------------------------------------
    // BORRAR SESIÓN
    // ----------------------------------------------------------

    /**
     * Borra toda la información de sesión.
     * Se llama desde PerfilActivity cuando el usuario pulsa "Cerrar sesión".
     * Después se redirige al LoginActivity y se limpia la pila de Activities.
     */
    public void clear() {
        prefs.edit().clear().apply();
    }
}