package com.tech.tp1retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.tech.tp1retrofit.databinding.ActivityMainBinding;
import com.tech.tp1retrofit.ui.perfil.PerfilViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    private PerfilViewModel perfilViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.nav_perfil, R.id.nav_inmuebles,
                R.id.nav_inquilinos, R.id.nav_contratos)
                .setOpenableLayout(binding.drawerLayout)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);


        View headerView = binding.navView.getHeaderView(0);
        TextView tvNombreHeader = headerView.findViewById(R.id.tvNombrePropietario);
        TextView tvEmailHeader = headerView.findViewById(R.id.tvEmailPropietario);

        perfilViewModel.getPropietario().observe(this, propietario -> {
            if (propietario != null) {
                tvNombreHeader.setText(propietario.getNombre() + " " + propietario.getApellido());
                tvEmailHeader.setText(propietario.getEmail());
            }
        });

        perfilViewModel.obtenerPerfil();

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}