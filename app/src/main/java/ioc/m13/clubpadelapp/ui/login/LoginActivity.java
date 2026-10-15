package ioc.m13.clubpadelapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ioc.m13.clubpadelapp.R;
import ioc.m13.clubpadelapp.data.api.ApiClient;
import ioc.m13.clubpadelapp.data.model.LoginRequest;
import ioc.m13.clubpadelapp.data.model.LoginResponse;
import ioc.m13.clubpadelapp.ui.pistas.PistasActivity;
import ioc.m13.clubpadelapp.util.TokenManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        progress = findViewById(R.id.progress);

        btnLogin.setOnClickListener(v -> doLogin());

        tvRegister.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }

    private void doLogin() {
        String email = etEmail.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        progress.setVisibility(View.VISIBLE);
        btnLogin.setEnabled(false);

        ApiClient.getService(this)
                .login(new LoginRequest(email, pass))
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> r) {
                        progress.setVisibility(View.GONE);
                        btnLogin.setEnabled(true);

                        if (r.isSuccessful() && r.body() != null) {
                            LoginResponse res = r.body();
                            new TokenManager(LoginActivity.this)
                                    .saveSession(res.getToken(), res.getRol(), res.getNombre());
                            startActivity(new Intent(LoginActivity.this, PistasActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "Credenciales incorrectas (" + r.code() + ")",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        progress.setVisibility(View.GONE);
                        btnLogin.setEnabled(true);
                        Toast.makeText(LoginActivity.this,
                                "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}