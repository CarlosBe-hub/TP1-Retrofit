package com.tech.tp1retrofit.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.tech.tp1retrofit.R;

public class PerfilFragment extends Fragment {
    public View onCreate(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState ){
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }
}
