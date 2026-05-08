package com.tech.tp1retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.tech.tp1retrofit.databinding.ActivityLoginBinding;
import com.tech.tp1retrofit.data.local.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sessionManager = new SessionManager(this);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        configurarObservadores();

        binding.btnLogin.setOnClickListener( v -> {
            String usuario = binding.etUsuario.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();

            viewModel.login(usuario, password);
        });
    }

    private void configurarObservadores() {
        viewModel.getTokenResult().observe(this, token -> {

            sessionManager.guardarToken(token);

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        viewModel.getErrorResult().observe(this, errorMensaje -> {
            Toast.makeText(LoginActivity.this, errorMensaje, Toast.LENGTH_LONG).show();
        });
    }
}
