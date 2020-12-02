package com.ana.discold;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.ana.discold.Beans.MessageBean;
import com.ana.discold.Beans.UserBean;
import com.ana.discold.WSUtils.OkHttpUtils;
import com.ana.discold.WSUtils.WSUtils;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    private final ArrayList<MessageBean> messageArrayList = new ArrayList<>();
    private MessageAdapter adapter = new MessageAdapter(messageArrayList);//esta lista é a q e passada ao adapter para ele mostrar no textview como eu lhe peco
    private RecyclerView rv;
    private EditText etMsg;
    private UserBean user; //valeur pseudo



//____ONCREATE()______________

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        //o adapter a controlar a rv
        rv.setAdapter(adapter);
        //a forma como se vai apresentar:linhas ou colonas.Neste caso linhas
        rv.setLayoutManager(new GridLayoutManager(this,1));
        //conteudo da msg
        etMsg = findViewById(R.id.etMsg);


//Recuperar pseudo que vem da login activity : bundle para incluir na msg
        if( getIntent().getExtras()!= null) {
            user = (UserBean) getIntent().getSerializableExtra("user");
            System.out.println("-------------------mainactivity o user tem guardado : " + user.getIdSession() + " id " + user.getId() + "name " + user.getPseudo() + "psw" + user.getPassword());
        } else {
            System.out.println("!!!!falta pseudo");
            finish();
            return;
        }

//chamar funçao de pedir a lista de msgs a cada 2 segundos para parecer um chat verdadeiro - pk nao estamos a usar websockets(socket.io, etc)
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                listMsgUpdated();
            }
        }, 0, 2000);//pour reload liste de msgs à tous les 2 secondes



    }//Fecha a oncreate

//clic btnSend+ enviar msg - envoyer message(Post)
    public void sendMessage(View view) {

        String msgContent = String.valueOf(etMsg.getText()); //tranformar o conteudo numa string para nao ser um editable
        MessageBean newMsg = new MessageBean(msgContent, user); //este user e criado na OnCreate

        Thread thread = new Thread(new Runnable() { //async  donc il faut un thread
            @Override
            public void run() {
                try {
                    WSUtils.sendMessageConv(newMsg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        etMsg.setText(""); //reset inputs
        listMsgUpdated(); //update listMsg
    }


    public void listMsgUpdated(){

        //les fonctions suivantes prendront du temps à recuperer des infos de l'internt, on utilise thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

//Recuperer liste de messages (Get)
                try  {
                    //Log.w("tag","funçao" +WSUtils.getListMsgConv());
                    messageArrayList.clear();
                    messageArrayList.addAll(WSUtils.getListMsgConv(user));

                    //changement graphique
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged(); //alert sur changements de données et faire reload
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    } //fecha listMsgUpdated

}//fecha classe
