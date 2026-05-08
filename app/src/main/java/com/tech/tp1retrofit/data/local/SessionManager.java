package com.tech.tp1retrofit.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "token_prefs"; //Nombre del archivo XML
    private static final String KEY_TOKEN = "token";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void guardarToken(String token){
        editor.putString(KEY_TOKEN, "Bearer " + token);
        editor.apply();
    }

    public String obtenerToken() {
        return sharedPreferences.getString(KEY_TOKEN, null);
    }

    public void cerrarSesion() {
        editor.remove(KEY_TOKEN);
        editor.apply();
    }
}
