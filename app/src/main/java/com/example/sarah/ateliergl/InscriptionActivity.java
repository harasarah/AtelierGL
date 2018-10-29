package com.example.sarah.ateliergl;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class InscriptionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
    }
    public void ChangeFragment(View view){
        Fragment fragment;

        if (view == findViewById(R.id.logo_client)){
            fragment = new ClientFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_inscription,fragment);
            ft.commit();

        }

        if (view == findViewById(R.id.logo_prestataire)){
            fragment = new PrestataireFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_inscription,fragment);
            ft.commit();
        }

    }
}
