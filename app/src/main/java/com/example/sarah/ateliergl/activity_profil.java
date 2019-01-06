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
import android.widget.Toast;

import com.example.sarah.ateliergl.network.GetPrestataireDataService;

import java.util.ArrayList;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_profil extends AppCompatActivity {
    ArrayList<Prestataire> allProfils;
    int i;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        ButterKnife.bind (this);
        final Bundle extras= getIntent().getExtras();

        String nom = new String((extras).getString("nom"));
        final Integer tel = new Integer((extras).getInt("tel"));
        String adresse = new String((extras).getString("adresse"));
        final float rating = new Float((extras).getFloat("rating"));

        TextView t_nom = (TextView) findViewById(R.id.nom);
        TextView t_adresse = (TextView) findViewById(R.id.adress);
        final RatingBar ratingBarProfil = findViewById(R.id.ratingBar2);

        t_nom.setText(nom);

        //ratingBarProfil.setRating(rating);

        ratingBarProfil.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                ratingBar.setRating(rating);
                //Log.d("Rating", "your selected value is :"+rating);
                rating = ratingBarProfil.getRating();

                GetPrestataireDataService service = com.example.sarah.ateliergl.network.RetrofitInstance.getRetrofitInstance().create( GetPrestataireDataService.class );


                Call<PrestataireList> call = service.getPrestataireData();
                call.enqueue( new Callback<PrestataireList>() {
                    @Override
                    public void onResponse(Call<PrestataireList> call, Response<PrestataireList> response) {
                        allProfils = (ArrayList<Prestataire>) response.body().getPrestataireArrayList();
                        PrestataireList p = new PrestataireList();
                        for (i=0; i<allProfils.size(); i++)
                            if (allProfils.get( i ).getCin().equals( extras.getString( "cin" ) ))
                            {
                                allProfils.get( i ).setRating( ratingBarProfil.getRating() );
                                break;
                            }
                        p.setPrestataireArrayList( allProfils );

                        GetPrestataireDataService service = com.example.sarah.ateliergl.network.RetrofitInstance.getRetrofitInstance().create( GetPrestataireDataService.class );
                        Call<PrestataireList> call2 = service.setPrestataireData( p );
                        call2.enqueue( new Callback<PrestataireList>() {
                            @Override
                            public void onResponse(Call<PrestataireList> call, Response<PrestataireList> response) {
                                Toast.makeText( getApplicationContext(), "", Toast.LENGTH_SHORT ).show();
                            }

                            @Override
                            public void onFailure(Call<PrestataireList> call, Throwable t) {
                                Toast.makeText( getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT ).show();
                            }
                        } );
                    }

                    @Override
                    public void onFailure(Call<PrestataireList> call, Throwable t) {
                        Toast.makeText( getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT ).show();
                    }
                } );

            }
        });
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
