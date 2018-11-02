package com.example.sarah.ateliergl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class ServiceActivity extends AppCompatActivity {

    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        cardView = findViewById(R.id.card_peinture);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_service= new Intent(ServiceActivity.this, ProfilsActivity.class);
                startActivity(intent_service);
            }
        });
    }
}
