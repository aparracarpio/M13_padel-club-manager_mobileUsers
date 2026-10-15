package ioc.m13.clubpadelapp.ui.pistas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ioc.m13.clubpadelapp.R;
import ioc.m13.clubpadelapp.data.api.ApiClient;
import ioc.m13.clubpadelapp.data.model.Pista;
import ioc.m13.clubpadelapp.ui.perfil.PerfilActivity;
import ioc.m13.clubpadelapp.ui.reservas.MisReservasActivity;
import ioc.m13.clubpadelapp.ui.reservas.ReservasActivity;
import ioc.m13.clubpadelapp.util.TokenManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PistasActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private PistaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pistas);

        // Saludo con el nombre del usuario
        TextView tvSaludo = findViewById(R.id.tvSaludo);
        String nombre = new TokenManager(this).getNombre();
        tvSaludo.setText("Hola, " + (nombre != null ? nombre : "jugador") + " 👋");

        // Botón perfil (arriba derecha)
        findViewById(R.id.btnPerfil).setOnClickListener(v ->
                startActivity(new Intent(this, PerfilActivity.class)));

        // RecyclerView
        recycler = findViewById(R.id.recyclerPistas);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PistaAdapter(new ArrayList<>(), pista -> {
            Intent i = new Intent(this, ReservasActivity.class);
            i.putExtra("idPista", pista.getId());
            i.putExtra("nombrePista", pista.getNombre());
            startActivity(i);
        });
        recycler.setAdapter(adapter);

        // FAB de mis reservas
        ExtendedFloatingActionButton fab = findViewById(R.id.fabMisReservas);
        fab.setOnClickListener(v ->
                startActivity(new Intent(this, MisReservasActivity.class)));

        cargarPistas();
    }

    private void cargarPistas() {
        ApiClient.getService(this).getPistas().enqueue(new Callback<List<Pista>>() {
            @Override
            public void onResponse(Call<List<Pista>> call, Response<List<Pista>> r) {
                if (r.isSuccessful() && r.body() != null) {
                    adapter.setPistas(r.body());
                } else {
                    Toast.makeText(PistasActivity.this,
                            "Error HTTP: " + r.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pista>> call, Throwable t) {
                Toast.makeText(PistasActivity.this,
                        "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}