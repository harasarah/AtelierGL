package com.example.sarah.ateliergl;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sarah.ateliergl.R;
import com.example.sarah.ateliergl.network.GetPrestataireDataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrestataireFragment extends Fragment {
    // static final Pattern EMAIL_ADDR = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])\n");
    EditText nom, prenom, phone, address, cin, password, ville, email;
    CheckBox pl, pe, me, el, ja;
    ArrayList<Prestataire> allProfils;
    String se;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate( R.layout.fragment_prestataire, container, false);

        nom =  view.findViewById(R.id.inscription_nom_cl);
        prenom =  view.findViewById(R.id.inscription_prenom_cl);
        phone =  view.findViewById(R.id.inscription_tel_cl);
        address =  view.findViewById(R.id.inscription_adresse);
        ville = view.findViewById(R.id.inscription_ville);
        cin =  view.findViewById(R.id.inscription_cin);
        email = view.findViewById(R.id.inscription_mail_cl);
        password =  view.findViewById(R.id.inscription_password);
        pe = view.findViewById(R.id.pe);
        ja = view.findViewById(R.id.ja);
        pl = view.findViewById(R.id.pl);
        me = view.findViewById(R.id.me);
        el = view.findViewById(R.id.el);



        Button button = (Button) view.findViewById(R.id.sign_in_pres);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox) pe).isChecked()) se = pe.getText().toString();
                if (((CheckBox) pl).isChecked()) se = pl.getText().toString();
                if (((CheckBox) el).isChecked()) se = el.getText().toString();
                if (((CheckBox) me).isChecked()) se = me.getText().toString();
                if (((CheckBox) ja).isChecked()) se = ja.getText().toString();

                final GetPrestataireDataService service = com.example.sarah.ateliergl.network.RetrofitInstance.getRetrofitInstance().create( GetPrestataireDataService.class );


                Call<PrestataireList> call = service.getPrestataireData();
                call.enqueue( new Callback<PrestataireList>() {
                    @Override
                    public void onResponse(Call<PrestataireList> call, Response<PrestataireList> response) {
                        allProfils = (ArrayList<Prestataire>) response.body().getPrestataireArrayList();
                        PrestataireList p = new PrestataireList();
                        allProfils.add(new Prestataire(nom.getText().toString(),prenom.getText().toString(),address.getText().toString(),ville.getText().toString(),Integer.parseInt(phone.getText().toString()),se,cin.getText().toString(),"client",password.getText().toString(),email.getText().toString(),""));
                        p.setPrestataireArrayList(allProfils);

                        GetPrestataireDataService service = com.example.sarah.ateliergl.network.RetrofitInstance.getRetrofitInstance().create( GetPrestataireDataService.class );
                        Call<PrestataireList> call2 = service.setPrestataireData(p);
                        call2.enqueue( new Callback<PrestataireList>() {
                            @Override
                            public void onResponse(Call<PrestataireList> call, Response<PrestataireList> response) {
                                startActivity(new Intent(getActivity(), ServiceActivity.class));
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
        });
        return view;

    }


}
