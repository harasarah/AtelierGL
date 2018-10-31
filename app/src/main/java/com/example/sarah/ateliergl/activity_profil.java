package com.example.sarah.ateliergl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.ButterKnife;

public class activity_profil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        ButterKnife.bind (this);
        Bundle extras= getIntent().getExtras();
        String nom = new String(((Bundle) extras).getString("nom"));
        String prenom = new String(((Bundle) extras).getString("prenom"));
        Integer tel = new Integer(((Bundle) extras).getInt("tel"));
        String adresse = new String(((Bundle) extras).getString("adresse"));


        TextView t_phone = (TextView) findViewById(R.id.phone);
        TextView t_nom = (TextView) findViewById(R.id.nom);
        TextView t_prenom = (TextView) findViewById(R.id.prenom);
        TextView t_adresse = (TextView) findViewById(R.id.adress);
        t_phone.setText(tel.toString());
        t_nom.setText(nom);
        t_prenom.setText(prenom);
        t_adresse.setText(adresse);



    }
}
