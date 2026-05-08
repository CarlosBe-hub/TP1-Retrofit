package com.tech.tp1retrofit.ui.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.tech.tp1retrofit.R;

public class InmuebleFragment extends Fragment {
    public View onCreate(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState ){
        return inflater.inflate(R.layout.fragment_inmuebles, container, false);
    }
}
