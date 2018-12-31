package com.example.sarah.ateliergl;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PrestataireList {
    @SerializedName("List")
    private ArrayList<Prestataire> prestataireList;

    public ArrayList<Prestataire> getPrestataireArrayList() {
        return prestataireList;
    }

    public void setPrestataireArrayList(ArrayList<Prestataire> prestataireArrayList) {
        this.prestataireList = prestataireArrayList;
    }
}
