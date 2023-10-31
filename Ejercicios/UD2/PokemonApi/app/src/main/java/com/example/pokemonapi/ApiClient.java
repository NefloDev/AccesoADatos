package com.example.pokemonapi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {
    @GET("cardinfo.php")
    Call<ApiResponse> getCards();

}
