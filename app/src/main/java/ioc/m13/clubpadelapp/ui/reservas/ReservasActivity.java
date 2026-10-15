package ioc.m13.clubpadelapp.ui.reservas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ioc.m13.clubpadelapp.R;
import ioc.m13.clubpadelapp.data.api.ApiClient;
import ioc.m13.clubpadelapp.data.model.ReservaRequest;
import ioc.m13.clubpadelapp.data.model.ReservaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservasActivity extends AppCompatActivity {

    private EditText etFecha, etHoraInicio, etHoraFin;
    private Button btnReservar;
    private Long idPista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        idPista = getIntent().getLongExtra("idPista", -1);
        String nombrePista = getIntent().getStringExtra("nombrePista");

        TextView tvPista = findViewById(R.id.tvPistaSeleccionada);
        tvPista.setText("Pista: " + nombrePista);

        etFecha = findViewById(R.id.etFecha);
        etHoraInicio = findViewById(R.id.etHoraInicio);
        etHoraFin = findViewById(R.id.etHoraFin);
        btnReservar = findViewById(R.id.btnReservar);

        btnReservar.setOnClickListener(v -> crearReserva());
    }

    private void crearReserva() {
        String fecha = etFecha.getText().toString().trim();
        // Si el usuario escribe "10/10/2026", conviértelo a "2026-10-10"
        if (fecha.contains("/")) {
            String[] partes = fecha.split("/");
            if (partes.length == 3) {
                fecha = partes[2] + "-" + partes[1] + "-" + partes[0];
            }
        }
        String hIni = etHoraInicio.getText().toString().trim();
        String hFin = etHoraFin.getText().toString().trim();

        if (fecha.isEmpty() || hIni.isEmpty() || hFin.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Aseguramos formato HH:mm:ss
        if (hIni.length() == 5) hIni += ":00";
        if (hFin.length() == 5) hFin += ":00";

        ReservaRequest req = new ReservaRequest(idPista, fecha, hIni, hFin);

        ApiClient.getService(this).crearReserva(req).enqueue(new Callback<ReservaResponse>() {
            @Override
            public void onResponse(Call<ReservaResponse> call, Response<ReservaResponse> r) {
                if (r.isSuccessful()) {
                    Toast.makeText(ReservasActivity.this,
                            "Reserva creada", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(ReservasActivity.this,
                            "Error: " + r.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ReservaResponse> call, Throwable t) {
                Toast.makeText(ReservasActivity.this,
                        "Fallo: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}