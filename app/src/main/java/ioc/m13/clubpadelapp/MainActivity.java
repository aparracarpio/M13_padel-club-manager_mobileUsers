package ioc.m13.clubpadelapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ioc.m13.clubpadelapp.ui.login.LoginActivity;
import ioc.m13.clubpadelapp.ui.pistas.PistasActivity;
import ioc.m13.clubpadelapp.util.TokenManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TokenManager tm = new TokenManager(this);

        if (tm.isLoggedIn()) {
            startActivity(new Intent(this, PistasActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }
}