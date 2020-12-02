package com.ana.discold.WSUtils;

import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8"); //vi na net - para indicar que e um JSON; e o tipo de eletras que leva, neste caso o utf8

    public static String sendGetOkHttpRequest(String url) throws Exception {
        Log.w("tag", "url : " + url );
        OkHttpClient client = new OkHttpClient();
        Request pedido = new Request.Builder().url(url).build();
        Response resposta = client.newCall(pedido).execute();
        if(resposta.code() < 200 || resposta.code() >=300) {
            throw new Exception("Incorrect " + resposta.code());
        } else {
            return resposta.body().string();

        }
    }



    public static String sendPostOkHttpRequest( String url, String json) throws Exception {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request pedido = new Request.Builder().url(url).post(body).build(); //construir o pedido
        Response resposta = client.newCall(pedido).execute();
        if(resposta.code() < 200 || resposta.code() >=300) {
            throw new Exception("Incorrect " + resposta.code());
        } else {
            return resposta.body().string();
        }
    }





}//fecha a classe
