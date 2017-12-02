package com.example.ajs00.ssmm_1718_practica2_g06;

/**
 * Created by usuario on 29/11/2017.
 */
//Aquí colocamos como viene la respuesta.
class ResponsePost {
    private String name;
    private String consulta;

    public class ResponsePost(){

    }

    public ResponsePost(String name, String consulta){
        this.name = name;
        this.consulta = consulta;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getConsulta(){
        return consulta;
    }

    public void setConsulta(String consulta){
        this.consulta = consulta;
    }


}
