package com.example.sarah.ateliergl;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.effect.Effect;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.ButterKnife;

public class activity_profil extends AppCompatActivity {
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        ButterKnife.bind (this);
        Bundle extras= getIntent().getExtras();

        String nom = new String((extras).getString("nom"));
        final Integer tel = new Integer((extras).getInt("tel"));
        String adresse = new String((extras).getString("adresse"));
        final float rating = new Float((extras).getFloat("rating"));

        TextView t_nom = (TextView) findViewById(R.id.nom);
        TextView t_adresse = (TextView) findViewById(R.id.adress);
        RatingBar ratingBarProfil = findViewById(R.id.ratingBar2);

        t_nom.setText(nom);
        ratingBarProfil.setRating(rating);
        t_adresse.setText(adresse);


        ImageView return_img = findViewById(R.id.return_img);
        return_img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                activity_profil.this.finish();
            }
        });


        ImageView callphone = findViewById(R.id.callphone);
        callphone.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                    Uri phoneNumber = Uri.parse( "tel:"+tel );
                    Intent call = new Intent(Intent.ACTION_CALL, phoneNumber);

                if (ActivityCompat.checkSelfPermission(activity_profil.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    Log.d( "test phone", "Calling" );
                    ActivityCompat.requestPermissions(activity_profil.this, new String[] {Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                startActivity(call);
            }
        });
    }
}
