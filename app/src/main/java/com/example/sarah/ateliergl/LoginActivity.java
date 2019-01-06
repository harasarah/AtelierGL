package com.example.sarah.ateliergl;

import retrofit2.Callback;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sarah.ateliergl.network.GetPrestataireDataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText login;
    EditText password;
    Button sign_in;
    Button inscription;
    ArrayList<Prestataire> allProfils;

    private void validate(final String log, final String pass) {
        final GetPrestataireDataService service = com.example.sarah.ateliergl.network.RetrofitInstance.getRetrofitInstance().create(GetPrestataireDataService.class);
        Call<PrestataireList> call = service.getPrestataireData();
        call.enqueue(new Callback<PrestataireList>() {
            @Override
            public void onResponse(Call<PrestataireList> call, Response<PrestataireList> response) {
                allProfils = response.body().getPrestataireArrayList();
                boolean test = false;
                for (Prestataire s : allProfils) {
                    if ((log.equals(s.mail)) && (pass.equals(s.mdp)) && (s.type_profil.equals("client"))) {
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("adresse", s.adresse).commit();
                        editor.putString("nom", s.nom).commit();
                        editor.putString("type_profil", s.type_profil).commit();
                        editor.putString("mdp", s.mdp).commit();
                        editor.putInt("tel", s.tel).commit();
                        editor.putString("cin", s.cin).commit();
                        editor.putString("mail", s.mail).commit();
                        Intent intent = new Intent(LoginActivity.this, ServiceActivity.class);
                        startActivity(intent);
                        finish();
                        test = true;
                        break;
                    }
                }
                if (test == false) {
                    Toast toast;
                    Context context = getApplicationContext();
                    String error_message = "Mot de passe ou login incorrecte, réessayez";
                    toast = Toast.makeText(context, error_message, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<PrestataireList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "erreur de connexion!", Toast.LENGTH_SHORT).show();
            }

        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        password = findViewById(R.id.edittext_password);

        login = findViewById(R.id.edittext_email);
        inscription = findViewById(R.id.inscription_link);


        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent_inscription = new Intent(LoginActivity.this, InscriptionActivity.class);
                startActivity(intent_inscription);
            }
        });


        sign_in = findViewById(R.id.confirmation_button);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(login.getText().toString().trim(), password.getText().toString().trim());

            }
        });
    }

}
