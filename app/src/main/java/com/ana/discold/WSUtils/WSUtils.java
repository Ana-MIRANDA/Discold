package com.ana.discold.WSUtils;

import android.util.Log;

import com.ana.discold.Beans.MessageBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

//WSUtils e onde se guardam as funcoes de webservicos, nomeadamente contactar servidores(neste caso o k eu criei no projeto)
//o OkHttpUtils so esta ligado ao WSUtils. Se um dia tiver d emudar de biblioteca(OKHttpUtils) para ouyra kk as mudanças de nome de bibliioteca so ocorrem qaui no WSUtils, dai a importancia de guardar as funcoes aqui neste documento
public class WSUtils {

    public static String teste() throws Exception {
        return OkHttpUtils.sendGetOkHttpRequest("http://93.0.193.180:8080/test");
    }

//enviar msgs serveur - POST! recebe uma messageBean, transforma-a no tipo String de formatoJson e manda p o servidor
    public static  void sendMessageConv(MessageBean msg) throws Exception {

       // String jsonAEnviar = "{\"user\":\"Teste android  \",\"content\":\"Post enviado a partir do android\"}";// um teste a mao para nao ter de converter nd ainda as \ e para se poder escrever caracteres especiais aracter literal escapes may be found in the section 3.10.6 of the JLS.

        Gson gson = new Gson();
        String msgConv = gson.toJson(msg); //transf a msg messageBean em Json;
        OkHttpUtils.sendPostOkHttpRequest("http://93.0.193.180:8080/envoyerMsg", msgConv);

    }

//Receber lista de mensagens - GET
    public static ArrayList<MessageBean> getListMsgConv() throws Exception {//Aqui nao pus dentro de try e catch pk esta funçao em mainactivity e chamada dentro deles

        String listMessages = OkHttpUtils.sendGetOkHttpRequest("http://93.0.193.180:8080/listeMsg" ); //tem de ser a morada do meu ip externo e nao localhost ou 127.0.0.1 q e a rede de todos os dispositivos ligados ao nosso router
                                                                                                            //listMsg é o endpoint definido no nosso serveur
                                                                                                            //listMessages e do tipo String (pk o retorno das APIs e sp uma string)e o que esta la é um array de objetos
        Gson gson = new Gson();
        ArrayList<MessageBean> resultArray = gson.fromJson(listMessages, new TypeToken<ArrayList<MessageBean>>(){}.getType()); // transf a string listMessage num array de objetos (de string a array) e getType() substitui o messageBean.class - vi na net

       // Log.w("tag", "mensagen content" + resultArray.get(0).getContent());  Log.w("tag", "mensagen id" + resultArray.get(0).getId());  Log.w("tag", "mensagen user" + resultArray.get(0).getUser().getPseudo());
        return resultArray;
    }
}
