package com.example.sarah.ateliergl.network;

import com.example.sarah.ateliergl.PrestataireList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetPrestataireDataService {
    @GET("get/ceNkpvxMya")
    Call<PrestataireList> getEmployeeData(@Query("indent") int indent);
}
