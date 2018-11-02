package com.example.sarah.ateliergl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {
    private ArrayList<Prestataire> prestataires;
    public MyAdapter(Context context, int ressource, ArrayList<Prestataire> prestataires){
        super(context, ressource, prestataires);
        this.prestataires = prestataires;
    }

    @NonNull
    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.cellule_profile, parent, false);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.ic_profil);
        imageView.setBackgroundResource(prestataires.get(position).getImageID());

        TextView textView = (TextView) convertView.findViewById(R.id.name);
        textView.setText((prestataires.get(position).getNom())+(prestataires.get(position).getPrenom()));

        return convertView;
    }


}
