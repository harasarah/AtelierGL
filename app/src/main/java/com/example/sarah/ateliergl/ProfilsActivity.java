package com.example.sarah.ateliergl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sarah.ateliergl.network.GetPrestataireDataService;
import com.example.sarah.ateliergl.network.RetrofitInstance;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*public class ProfilsActivity extends AppCompatActivity {
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
}*/

public class ProfilsActivity extends AppCompatActivity {

    private MyAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profils);

        /*Create handle for the RetrofitInstance interface*/
        GetPrestataireDataService service = RetrofitInstance.getRetrofitInstance().create(GetPrestataireDataService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<PrestataireList> call = service.getEmployeeData(100);

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<PrestataireList>() {
            @Override
            public void onResponse(Call<PrestataireList> call, Response<PrestataireList> response) {
                generateEmployeeList(response.body().getPrestataireArrayList());
            }

            @Override
            public void onFailure(Call<PrestataireList> call, Throwable t) {
                Toast.makeText(ProfilsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of employees using RecyclerView with custom adapter*/
    private void generateEmployeeList(ArrayList<Prestataire> empDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_prestataire_list);

        adapter = new MyAdapter(empDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProfilsActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}
