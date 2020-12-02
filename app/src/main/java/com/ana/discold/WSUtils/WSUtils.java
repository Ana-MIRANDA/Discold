package com.ana.discold.WSUtils;

import android.util.Log;

import com.ana.discold.Beans.ErrorBean;
import com.ana.discold.Beans.MessageBean;
import com.ana.discold.Beans.UserBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URL;
import java.util.ArrayList;

//WSUtils e onde se guardam as funcoes de webservicos, nomeadamente contactar servidores(neste caso o k eu criei no projeto)
//o OkHttpUtils so esta ligado ao WSUtils. Se um dia tiver d emudar de biblioteca(OKHttpUtils) para ouyra kk as mudanças de nome de bibliioteca so ocorrem qaui no WSUtils, dai a importancia de guardar as funcoes aqui neste documento
public class WSUtils {
    //constante avec l'url
    static final String URL ="http://93.0.193.180:8080/";
    
//TESTE
    public static String teste() throws Exception {
        return OkHttpUtils.sendGetOkHttpRequest(URL + "test");
    }

//enviar msgs serveur - POST! recebe uma messageBean, transforma-a no tipo String de formatoJson e manda p o servidor
    public static void sendMessageConv(MessageBean msg) throws Exception {
        Gson gson = new Gson();
        String msgConv = gson.toJson(msg); //transf a msg messageBean em Json;
        OkHttpUtils.sendPostOkHttpRequest(URL + "envoyerMsg", msgConv);
    }

//Receber lista de mensagens - GET
    public static ArrayList<MessageBean> getListMsgConv(UserBean u) throws Exception {//Aqui nao pus dentro de try e catch pk esta funçao em mainactivity e chamada dentro deles
        Gson gson = new Gson();
        String uConv = gson.toJson(u); //transf user userbean en string format Json;
        String listMessages = OkHttpUtils.sendPostOkHttpRequest(URL + "listeMsg", uConv);
        ArrayList<MessageBean> resultArray = gson.fromJson(listMessages, new TypeToken<ArrayList<MessageBean>>(){}.getType()); // transf a string listMessage num array de objetos (de string a array) e getType() substitui o messageBean.class - vi na net

        return resultArray;
    }

//verificar os dados de login pseudo e pass

    public static UserBean verifyLogin(UserBean u) throws Exception {
        Gson gson = new Gson();
        String uConv = gson.toJson(u); //transf user userbean en string format Json;
        String result = OkHttpUtils.sendPostOkHttpRequest(URL + "login", uConv); //obj userbean com proporiedade idSession

        if(result.indexOf("errorMessage") != -1){ //sil y a un erreur
            return null;
            /*ErrorBean error = gson.fromJson(result, ErrorBean.class); //transf dee l'erreur msg de string format Json en Objet
            throw new Exception(error.getErrorMessage());*/
        }
        UserBean user = gson.fromJson(result, UserBean.class); //transf de string format Json em Objet

        return user;
    }

    //creer l xml et tester
    public static UserBean register(UserBean u) throws Exception {
        Gson gson = new Gson();
        String uConv = gson.toJson(u); //transf user userbean en string format Json;
        String result = OkHttpUtils.sendPostOkHttpRequest(URL + "register", uConv); //obj userbean com proporiedade idSession

        if(result.indexOf("errorMessage") != -1){ //sil y a un erreur
            ErrorBean error = gson.fromJson(result, ErrorBean.class); //transf dee l'erreur msg de string format Json en Objet
            throw new Exception(error.getErrorMessage());
        }
        UserBean user = gson.fromJson(result, UserBean.class); //transf de string format Json em Objet

        return user;
    }


}
