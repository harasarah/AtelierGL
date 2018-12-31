package com.example.sarah.ateliergl;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sarah.ateliergl.R;

public class PrestataireFragment extends Fragment {
    // static final Pattern EMAIL_ADDR = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])\n");
    EditText nom, prenom, phone;
    EditText email;
    private boolean validate (EditText email){

        if (email.toString().isEmpty()){
            email.setError("champs vide");
            return false;

        } else {
            if ( Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches() || Patterns.PHONE.matcher(email.toString()).matches()) {
                email.setError(null);
                return true;
            }
            else {

                email.setError("entrer un vrai mail ou numero");
                return false;
            }


        }
    }
    private boolean validate1 (EditText nom){

        if (nom.toString().isEmpty()){
            nom.setError("champs vide");
            return false;

        } else {
            if ( Patterns.EMAIL_ADDRESS.matcher(nom.toString()).matches()) {
                nom.setError(null);
                return true;
            }
            else {
                nom.setError("seulement les lettres sont autorisés");
                return false;
            }


        }
    }
    private boolean validate2 (EditText phone){

        if (phone.toString().isEmpty()){
            phone.setError("champs vide");
            return false;

        } else {
            if ( Patterns.PHONE.matcher(phone.toString()).matches()) {
                phone.setError(null);
                return true;
            }
            else {
                phone.setError("seulement les lettres sont autorisés");
                return false;
            }


        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate( R.layout.fragment_prestataire, container, false);

        nom =  view.findViewById(R.id.inscription_nom_cl);
        prenom =  view.findViewById(R.id.inscription_prenom_cl);
        phone =  view.findViewById(R.id.inscription_tel_cl);
        Button button = (Button) view.findViewById(R.id.sign_in_pres);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = getView().findViewById(R.id.inscription_mail_cl);
                validate(email);
                validate1(nom);
                validate1(prenom);
                validate2(phone);
            }
        });
        return view;

    }


}
