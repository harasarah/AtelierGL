package com.example.sarah.ateliergl;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.sarah.ateliergl.Prestataire;
import com.example.sarah.ateliergl.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/* public class MyAdapter extends ArrayAdapter {
    private ArrayList<Prestataire> prestataires;
    public MyAdapter(Context context, int ressource, ArrayList<Prestataire> prestataires){
        super(context, ressource, prestataires);
        this.prestataires = prestataires;
    }

    @NonNull
    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate( R.layout.cellule_profile, parent, false);

        de.hdodenhof.circleimageview.CircleImageView imageView = convertView.findViewById(R.id.ic_profil);
        imageView.setImageResource(prestataires.get(position).getImageID());

        TextView textView = (TextView) convertView.findViewById(R.id.name);
        textView.setText((prestataires.get(position).getNom())+(prestataires.get(position).getPrenom()));

        return convertView;
    }
} */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PrestataireViewHolder> {

     ArrayList<Prestataire> dataList;
     Context context;

    public MyAdapter(ArrayList<Prestataire> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public PrestataireViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cellule_profile, parent, false);
        return new PrestataireViewHolder(view);
    }
    @Override
    public void onBindViewHolder(PrestataireViewHolder holder, final int position) {
        holder.txtNom.setText(dataList.get(position).getNom());
        holder.ratingBar.setRating(dataList.get(position).getRating());
        //  holder.txtPrenom.setText(dataList.get(position).getPrenom());
        //  holder.txtPhone.setText(dataList.get(position).getTel());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( context, activity_profil.class );
                Prestataire pres = dataList.get(position);
                intent.putExtra( "nom", pres.nom);

                intent.putExtra( "prenom", pres.tel );
                intent.putExtra("cin", pres.cin);
              //  intent.putExtra("imageID", pres.imageID);
                intent.putExtra("adresse", pres.adresse);
                intent.putExtra("tel", pres.tel);
                intent.putExtra("password", pres.mdp);
                intent.putExtra("profession", pres.type_profil);
                intent.putExtra("rating",pres.rating);
              //   intent.putExtra("description", pres.description);
                context.startActivity(intent);
            }
        });
    };


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class PrestataireViewHolder extends RecyclerView.ViewHolder {

        TextView txtNom, txtPrenom , txtPhone;
        RatingBar ratingBar;

        PrestataireViewHolder(View itemView) {
            super(itemView);
            txtNom = (TextView) itemView.findViewById(R.id.nom);
            ratingBar = itemView.findViewById(R.id.ratingx);
          //  txtPrenom = (TextView) itemView.findViewById(R.id.prenom);
         //  txtPhone = (TextView) itemView.findViewById(R.id.txt_employee_phone);
        }
    }
}


