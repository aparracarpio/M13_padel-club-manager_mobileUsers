package ioc.m13.clubpadelapp.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ioc.m13.clubpadelapp.R;
import ioc.m13.clubpadelapp.ui.login.LoginActivity;
import ioc.m13.clubpadelapp.util.TokenManager;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        TokenManager tm = new TokenManager(this);

        TextView tvNombre = findViewById(R.id.tvPerfilNombre);
        TextView tvRol = findViewById(R.id.tvPerfilRol);
        Button btnLogout = findViewById(R.id.btnLogout);

        tvNombre.setText(tm.getNombre());
        tvRol.setText(tm.getRol());

        btnLogout.setOnClickListener(v -> {
            tm.clear();
            Intent i = new Intent(this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();
        });
    }
}