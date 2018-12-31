package com.example.sarah.ateliergl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

    public MyAdapter(ArrayList<Prestataire> dataList) {
        this.dataList = dataList;
    }

    @Override
    public PrestataireViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cellule_profile, parent, false);
        return new PrestataireViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PrestataireViewHolder holder, int position) {
        holder.txtNom.setText(dataList.get(position).getNom());
      //  holder.txtPrenom.setText(dataList.get(position).getPrenom());
      //  holder.txtPhone.setText(dataList.get(position).getTel());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class PrestataireViewHolder extends RecyclerView.ViewHolder {

        TextView txtNom, txtPrenom , txtPhone;

        PrestataireViewHolder(View itemView) {
            super(itemView);
            txtNom = (TextView) itemView.findViewById(R.id.nom);
          //  txtPrenom = (TextView) itemView.findViewById(R.id.prenom);
         //  txtPhone = (TextView) itemView.findViewById(R.id.txt_employee_phone);
        }
    }
}


