package com.example.sarah.ateliergl;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PrestataireViewHolder> implements Filterable {

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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, activity_profil.class);
                Prestataire pres = dataList.get(position);
                intent.putExtra("nom", pres.nom);
                intent.putExtra("prenom", pres.tel);
                intent.putExtra("cin", pres.cin);
                intent.putExtra("adresse", pres.adresse);
                intent.putExtra("tel", pres.tel);
                intent.putExtra("password", pres.mdp);
                intent.putExtra("profession", pres.type_profil);
                intent.putExtra("rating", pres.rating);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                dataList = (ArrayList<Prestataire>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Prestataire> FilteredArrList = new ArrayList<>();

                if (dataList == null) {
                    dataList = new ArrayList<>(dataList); // saves the original data in dataList
                }

                if (constraint == null || constraint.length() == 0) {

                    results.count = dataList.size();
                    results.values = dataList;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < dataList.size(); i++) {
                        String data = dataList.get(i).nom;
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new Prestataire(dataList.get(i).nom, dataList.get(i).nom, dataList.get(i).nom, dataList.get(i).nom, dataList.get(i).tel, dataList.get(i).nom, dataList.get(i).nom, dataList.get(i).nom, dataList.get(i).nom, dataList.get(i).nom, dataList.get(i).rating));
                        }
                    }
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }


    class PrestataireViewHolder extends RecyclerView.ViewHolder {

        TextView txtNom;
        RatingBar ratingBar;

        PrestataireViewHolder(View itemView) {
            super(itemView);
            txtNom = itemView.findViewById(R.id.nom);
            ratingBar = itemView.findViewById(R.id.ratingx);

        }
    }


}


