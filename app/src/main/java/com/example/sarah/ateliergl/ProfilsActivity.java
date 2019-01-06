package com.example.sarah.ateliergl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sarah.ateliergl.network.GetPrestataireDataService;
import com.example.sarah.ateliergl.network.RetrofitInstance;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilsActivity extends AppCompatActivity {

    private MyAdapter adapter;
    @BindView(R.id.recycler_view_prestataire_list)
    RecyclerView recyclerView;
    ArrayList<Prestataire> allProfils;
    MaterialSearchBar searchBarmt;
    RecyclerView listPrestataire;


    String se;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profils);

        initialize();

        searchBarmt.addTextChangeListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Call back the Adapter with current character to Filter
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



        /*Create handle for the RetrofitInstance interface*/
        GetPrestataireDataService service = RetrofitInstance.getRetrofitInstance().create(GetPrestataireDataService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<PrestataireList> call = service.getPrestataireData();

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<PrestataireList>() {
            @Override
            public void onResponse(Call<PrestataireList> call, Response<PrestataireList> response) {
                generatePrestataireList(response.body().getPrestataireArrayList());

                allProfils = response.body().getPrestataireArrayList();


            }

            @Override
            public void onFailure(Call<PrestataireList> call, Throwable t) {
                Toast.makeText(ProfilsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
        Button click_map = findViewById(R.id.map);
        click_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Prestataire> list = allProfils;
                Intent intent_map = new Intent(getApplicationContext(), MapsActivity.class);
                intent_map.putExtra("LIST", (Serializable) list);
                startActivity(intent_map);
            }
        });
    }

    private void initialize() {
        searchBarmt = findViewById(R.id.searchBar);
        listPrestataire = findViewById(R.id.recycler_view_prestataire_list);
    }

    /*Method to generate List of employees using RecyclerView with custom adapter*/
    void generatePrestataireList(ArrayList<Prestataire> empDataList) {
        se = getIntent().getStringExtra("se");
        ArrayList<Prestataire> empDataListFiltered = new ArrayList<>();
        for (Prestataire p : empDataList) {
            if (p.service == null) continue;
            if (p.service.equals(se)) empDataListFiltered.add(p);
        }

        recyclerView = findViewById(R.id.recycler_view_prestataire_list);

        adapter = new MyAdapter(empDataListFiltered, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProfilsActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        GetPrestataireDataService service = RetrofitInstance.getRetrofitInstance().create(GetPrestataireDataService.class);
        Call<PrestataireList> call = service.getPrestataireData();
        call.enqueue(new Callback<PrestataireList>() {
            @Override
            public void onResponse(Call<PrestataireList> call, Response<PrestataireList> response) {
                generatePrestataireList(response.body().getPrestataireArrayList());
                allProfils = response.body().getPrestataireArrayList();
            }

            @Override
            public void onFailure(Call<PrestataireList> call, Throwable t) {
                Toast.makeText(ProfilsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}