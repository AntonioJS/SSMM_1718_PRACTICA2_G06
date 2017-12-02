package com.example.ajs00.ssmm_1718_practica2_g06;


import java.util.List;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by usuario on 29/11/2017.
 */

public interface SOService {
    //Interfaz
    //Colocamos el end point de la página, lo que está después de la ultima barra.
    @GET("autentica.php?user=user&pass=12345")
    //entre los simbolos colocamos como va a venir la respuesta.
    Call<List<ResponsePost>> getUsersGet();


}
