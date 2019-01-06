package com.example.sarah.ateliergl.network;

import com.example.sarah.ateliergl.PrestataireList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GetPrestataireDataService {
    @GET("14t2ug")
    Call<PrestataireList> getPrestataireData();

    @PUT("14t2ug")
    Call<PrestataireList> setPrestataireData(@Body PrestataireList listPrestataires);

}
