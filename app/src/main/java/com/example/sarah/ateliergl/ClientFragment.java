package com.example.sarah.ateliergl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sarah.ateliergl.R;
import com.example.sarah.ateliergl.network.GetPrestataireDataService;
import com.example.sarah.ateliergl.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ClientFragment extends Fragment {
    EditText nom, prenom, phone, email, password, passwordC, address, ville, cin;
    ArrayList<Prestataire> allProfils;

    private boolean validate(EditText email) {

        if (email.toString().isEmpty()) {
            email.setError( "champs vide" );
            return false;

        } else {
            if (Patterns.EMAIL_ADDRESS.matcher( email.toString() ).matches() || Patterns.PHONE.matcher( email.toString() ).matches()) {
                email.setError( null );
                return true;
            } else {

                email.setError( "entrer un vrai mail ou numero" );
                return false;
            }


        }
    }

    private boolean validate1(EditText nom) {

        if (nom.toString().isEmpty()) {
            nom.setError( "champs vide" );
            return false;

        } else {
            if (Patterns.EMAIL_ADDRESS.matcher( nom.toString() ).matches()) {
                nom.setError( null );
                return true;
            } else {
                nom.setError( "seulement les lettres sont autorisés" );
                return false;
            }


        }
    }

    private boolean validate2(EditText phone) {

        if (phone.toString().isEmpty()) {
            phone.setError( "champs vide" );
            return false;

        } else {
            if (Patterns.PHONE.matcher( phone.toString() ).matches()) {
                phone.setError( null );
                return true;
            } else {
                phone.setError( "seulement les lettres sont autorisés" );
                return false;
            }


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_client, container, false );
        nom = view.findViewById( R.id.inscription_nom_cl );
        prenom = view.findViewById( R.id.inscription_prenom_cl );
        phone = view.findViewById( R.id.inscription_tel_cl );
        address = view.findViewById( R.id.inscription_adresse );
        ville = view.findViewById( R.id.inscription_ville );
        cin = view.findViewById( R.id.inscription_cin );
        password = view.findViewById( R.id.inscription_password );

        Button button = (Button) view.findViewById( R.id.sign_in_cl );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = getView().findViewById( R.id.inscription_mail_cl );


                GetPrestataireDataService service = com.example.sarah.ateliergl.network.RetrofitInstance.getRetrofitInstance().create( GetPrestataireDataService.class );


                Call<PrestataireList> call = service.getPrestataireData();
                call.enqueue( new Callback<PrestataireList>() {
                    @Override
                    public void onResponse(Call<PrestataireList> call, Response<PrestataireList> response) {
                        allProfils = (ArrayList<Prestataire>) response.body().getPrestataireArrayList();
                        PrestataireList p = new PrestataireList();
                        allProfils.add( new Prestataire( nom.getText().toString(), prenom.getText().toString(), address.getText().toString(), ville.getText().toString(), Integer.parseInt( phone.getText().toString() ), "", cin.getText().toString(), "client", password.getText().toString(), email.getText().toString(), 5 ) );
                        p.setPrestataireArrayList( allProfils );

                        GetPrestataireDataService service = com.example.sarah.ateliergl.network.RetrofitInstance.getRetrofitInstance().create( GetPrestataireDataService.class );
                        Call<PrestataireList> call2 = service.setPrestataireData( p );
                        call2.enqueue( new Callback<PrestataireList>() {
                            @Override
                            public void onResponse(Call<PrestataireList> call, Response<PrestataireList> response) {
                                startActivity( new Intent( getActivity(), ProfilsActivity.class ) );
                            }

                            @Override
                            public void onFailure(Call<PrestataireList> call, Throwable t) {
                                Toast.makeText( getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT ).show();
                            }
                        } );
                    }

                    @Override
                    public void onFailure(Call<PrestataireList> call, Throwable t) {
                        Toast.makeText( getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT ).show();
                    }
                } );

            }

        } );
        return view;

    }
}