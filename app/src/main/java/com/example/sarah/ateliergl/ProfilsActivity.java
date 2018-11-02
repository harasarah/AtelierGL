package com.example.sarah.ateliergl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ProfilsActivity extends AppCompatActivity {
    @BindView(R.id.profilslist)
    ListView listPrestataire ;
    ArrayList<Prestataire> prestataires = new ArrayList<Prestataire>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profils);
        ButterKnife.bind (this);

        prestataires.add(new Prestataire(R.drawable.othmane_avatar, "Feriani ", "Othmane", "adresse, rue, ville", 0606060606, "password", "G688851", "plomberie", "description"));
        prestataires.add(new Prestataire(R.drawable.rifi_avatar, "Rifi ", "Zainab", "adresse, rue, ville", 0606060606, "password", "G688851", "plomberie", "description"));
        prestataires.add(new Prestataire(R.drawable.logo_avatar_client, "Mouqine ", "Sarah", "adresse, rue, ville", 0606060606, "password", "G688851", "plomberie", "description"));

        MyAdapter adapter;
        adapter = new MyAdapter(getApplicationContext(), R.layout.cellule_profile, prestataires );
        listPrestataire.setAdapter(adapter);
    }

    @OnItemClick(R.id.profilslist)
    public void onListClicked(int position){
        Intent intent= new Intent(ProfilsActivity.this, activity_profil.class);
        Prestataire pres= prestataires.get(position);
        intent.putExtra("nom", pres.nom);
        intent.putExtra("prenom", pres.prenom);
        intent.putExtra("cin", pres.cin);
        intent.putExtra("imageID", pres.imageID);
        intent.putExtra("adresse", pres.adresse);
        intent.putExtra("tel", pres.tel);
        intent.putExtra("password", pres.password);
        intent.putExtra("profession", pres.profession);
        intent.putExtra("description", pres.description);
        startActivity(intent);
    }
}
