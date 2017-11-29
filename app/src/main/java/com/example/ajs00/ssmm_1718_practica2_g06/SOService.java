package com.example.ajs00.ssmm_1718_practica2_g06;


import okhttp3.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by usuario on 29/11/2017.
 */

public interface SOService {

String URL = " http://www4.ujaen.es/~jccuevas/ssmm/autentica.php?user=user&pass=12345";
    @GET("LLAMADAGET")
    Call<ResponsePost> hacerLlamada(@Body RequestPost);



}
