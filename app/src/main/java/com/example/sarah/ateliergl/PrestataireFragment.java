package com.example.sarah.ateliergl;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.sarah.ateliergl.network.GetPrestataireDataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrestataireFragment extends Fragment {
    EditText nom, prenom, phone, address, cin, password, ville, email;
    CheckBox pl, pe, me, el, ja;
    ArrayList<Prestataire> allProfils;
    String se;
    RatingBar rating;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_prestataire, container, false);

        nom = view.findViewById(R.id.inscription_nom_cl);
        prenom = view.findViewById(R.id.inscription_prenom_cl);
        phone = view.findViewById(R.id.inscription_tel_cl);
        address = view.findViewById(R.id.inscription_adresse);
        ville = view.findViewById(R.id.inscription_ville);
        cin = view.findViewById(R.id.inscription_cin);
        email = view.findViewById(R.id.inscription_mail_cl);
        password = view.findViewById(R.id.inscription_password);
        pe = view.findViewById(R.id.pe);
        ja = view.findViewById(R.id.ja);
        pl = view.findViewById(R.id.pl);
        me = view.findViewById(R.id.me);
        el = view.findViewById(R.id.el);
        rating = view.findViewById(R.id.ratingx);


        Button button = view.findViewById(R.id.sign_in_pres);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((pe).isChecked()) se = pe.getText().toString();
                if ((pl).isChecked()) se = pl.getText().toString();
                if ((el).isChecked()) se = el.getText().toString();
                if ((me).isChecked()) se = me.getText().toString();
                if ((ja).isChecked()) se = ja.getText().toString();

                final GetPrestataireDataService service = com.example.sarah.ateliergl.network.RetrofitInstance.getRetrofitInstance().create(GetPrestataireDataService.class);


                Call<PrestataireList> call = service.getPrestataireData();
                call.enqueue(new Callback<PrestataireList>() {
                    @Override
                    public void onResponse(Call<PrestataireList> call, Response<PrestataireList> response) {
                        allProfils = response.body().getPrestataireArrayList();
                        PrestataireList p = new PrestataireList();
                        allProfils.add(new Prestataire(nom.getText().toString(), prenom.getText().toString(), address.getText().toString(), ville.getText().toString(), Integer.parseInt(phone.getText().toString()), se, cin.getText().toString(), "client", password.getText().toString(), email.getText().toString(), 0));
                        p.setPrestataireArrayList(allProfils);


                        GetPrestataireDataService service = com.example.sarah.ateliergl.network.RetrofitInstance.getRetrofitInstance().create(GetPrestataireDataService.class);
                        Call<PrestataireList> call2 = service.setPrestataireData(p);
                        call2.enqueue(new Callback<PrestataireList>() {
                            @Override
                            public void onResponse(Call<PrestataireList> call, Response<PrestataireList> response) {
                                startActivity(new Intent(getActivity(), ServiceActivity.class));
                            }

                            @Override
                            public void onFailure(Call<PrestataireList> call, Throwable t) {
                                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<PrestataireList> call, Throwable t) {
                        Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;

    }


}
