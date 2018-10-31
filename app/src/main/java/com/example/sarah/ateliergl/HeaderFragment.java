package com.example.sarah.ateliergl;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class HeaderFragment extends Fragment {
    View rootView;
    public HeaderFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_header,container,false);
        ImageView imageView = rootView.findViewById(R.id.imageView22);
        imageView.setImageResource(R.drawable.logo_service_good);

        return rootView;
    }

}