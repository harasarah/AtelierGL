package com.example.sarah.ateliergl.network;

import com.example.sarah.ateliergl.PrestataireList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GetPrestataireDataService {
    @GET("fpjrg")
    Call<PrestataireList> getPrestataireData();

    @PUT("fpjrg")
    Call<PrestataireList> setPrestataireData(@Body PrestataireList listPrestataires);

    @PATCH("fpjrg")
    Call<PrestataireList> updatetPrestataireData(@Path("rating") float rating,@Body PrestataireList listPrestataires);
}
