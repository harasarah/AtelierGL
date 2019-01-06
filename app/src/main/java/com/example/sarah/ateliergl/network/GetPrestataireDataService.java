package com.example.sarah.ateliergl.network;

import com.example.sarah.ateliergl.PrestataireList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GetPrestataireDataService {
    @GET("q1j1k")
    Call<PrestataireList> getPrestataireData();

    @PUT("q1j1k")
    Call<PrestataireList> setPrestataireData(@Body PrestataireList listPrestataires);

}
