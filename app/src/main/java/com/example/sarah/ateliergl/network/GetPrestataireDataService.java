package com.example.sarah.ateliergl.network;

import com.example.sarah.ateliergl.PrestataireList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface GetPrestataireDataService {
    @GET("iyfbo")
    Call<PrestataireList> getPrestataireData();

    @PUT("iyfbo")
    Call<PrestataireList> setPrestataireData(@Body PrestataireList listPrestataires);
}
