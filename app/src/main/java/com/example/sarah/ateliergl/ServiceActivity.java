package com.example.sarah.ateliergl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    CardView cardPe, cardPl, cardJa, cardEl, cardMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        cardPe = findViewById(R.id.card_peinture);
        cardPl = findViewById(R.id.card_plombier);
        cardJa = findViewById(R.id.card_jardinier);
        cardEl = findViewById(R.id.card_electricite);
        cardMe = findViewById(R.id.card_menage);
        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String se = ((TextView) view.findViewWithTag("se")).getText().toString();
                Intent intent_service = new Intent(ServiceActivity.this, ProfilsActivity.class);
                intent_service.putExtra("se", se);
                startActivity(intent_service);
            }
        };
        cardPe.setOnClickListener(l);
        cardPl.setOnClickListener(l);
        cardMe.setOnClickListener(l);
        cardEl.setOnClickListener(l);
        cardJa.setOnClickListener(l);
    }
}