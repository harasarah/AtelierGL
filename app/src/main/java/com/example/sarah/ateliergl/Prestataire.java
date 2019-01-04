package com.example.sarah.ateliergl;

import com.google.gson.annotations.SerializedName;

public class Prestataire {
    @SerializedName("nom")
    String nom;
    //@SerializedName("prenom")
   // String prenom;
    @SerializedName("adresse")
    String adresse;
    @SerializedName("ville")
    String ville;
    @SerializedName("tel")
    Integer tel;
    @SerializedName("service")
    String service;
    @SerializedName("cin")
    String cin;
    @SerializedName("type_profil")
    String type_profil;
    @SerializedName("mdp")
    String mdp;
    @SerializedName("mail")
    String mail;
    @SerializedName("rating")
    float rating;

    public Prestataire(String nom, String prenom, String adresse, String ville, Integer tel, String service,String cin, String type_profil, String mdp, String mail, float rating) {
       // this.imageID = imageID;
        this.nom = nom;
     //   this.prenom = prenom;
        this.adresse = adresse;
        this.ville = ville;
        this.tel = tel;
        this.service = service;
        this.cin = cin;
        this.type_profil = type_profil;
        this.mdp = mdp;
        this.mail = mail;
        this.rating = rating;
    }






    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    /*public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }*/

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String adresse) {
        this.ville = ville;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getType_profil() {
        return type_profil;
    }

    public void setType_profil(String type_profil) {
        this.type_profil = type_profil;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
