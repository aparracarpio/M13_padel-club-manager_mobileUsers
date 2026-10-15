package ioc.m13.clubpadelapp.ui.reservas;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ioc.m13.clubpadelapp.R;
import ioc.m13.clubpadelapp.data.api.ApiClient;
import ioc.m13.clubpadelapp.data.model.ReservaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MisReservasActivity extends AppCompatActivity {

    private ReservaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_reservas);

        RecyclerView recycler = findViewById(R.id.recyclerReservas);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ReservaAdapter(new ArrayList<>(), id -> cancelarReserva(id));
        recycler.setAdapter(adapter);

        cargarReservas();
    }

    private void cargarReservas() {
        ApiClient.getService(this).getMisReservas().enqueue(new Callback<List<ReservaResponse>>() {
            @Override
            public void onResponse(Call<List<ReservaResponse>> call, Response<List<ReservaResponse>> r) {
                if (r.isSuccessful() && r.body() != null) {
                    adapter.setReservas(r.body());
                }
            }

            @Override
            public void onFailure(Call<List<ReservaResponse>> call, Throwable t) {
                Toast.makeText(MisReservasActivity.this,
                        "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void cancelarReserva(Long id) {
        ApiClient.getService(this).cancelarReserva(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> r) {
                if (r.isSuccessful()) {
                    Toast.makeText(MisReservasActivity.this,
                            "Reserva cancelada", Toast.LENGTH_SHORT).show();
                    cargarReservas();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MisReservasActivity.this,
                        "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}