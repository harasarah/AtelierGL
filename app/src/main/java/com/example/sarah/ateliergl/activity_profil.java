package com.example.sarah.ateliergl;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.effect.Effect;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;

public class activity_profil extends AppCompatActivity {
    EditText phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        ButterKnife.bind (this);
        Bundle extras= getIntent().getExtras();
        String nom = new String(((Bundle) extras).getString("nom"));
        String prenom = new String(((Bundle) extras).getString("prenom"));
        final Integer tel = new Integer(((Bundle) extras).getInt("tel"));
        String adresse = new String(((Bundle) extras).getString("adresse"));


        TextView t_phone = (TextView) findViewById(R.id.phone);
        TextView t_nom = (TextView) findViewById(R.id.nom);
        TextView t_prenom = (TextView) findViewById(R.id.prenom);
        TextView t_adresse = (TextView) findViewById(R.id.adress);
        t_phone.setText(tel.toString());
        t_nom.setText(nom);
        t_prenom.setText(prenom);
        t_adresse.setText(adresse);

        Button callButton = (Button)findViewById(R.id.btnCall);
        callButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    Uri phoneNumber = Uri.parse( "tel:"+tel );
                    Intent call = new Intent(Intent.ACTION_CALL, phoneNumber);
                    startActivity(call);
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Calling a Phone Number", "Call failed", activityException);
                }

            }
        });
    }
}
