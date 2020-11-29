package com.ana.discold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ana.discold.Beans.UserBean;
import com.ana.discold.WSUtils.WSUtils;

public class LogInActivity extends AppCompatActivity {

    private EditText etUserPseudo;
    private EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }//fecha a oncreate

//ir p o chat depois de indicar pseudo
    public void goChat(View view) {

        etUserPseudo=findViewById(R.id.etUserPseudo);
        String userPseudo = String.valueOf(etUserPseudo.getText()); //nao poder ser so geText() pk por defeito e do tipo editable e eu quero String

        etPassword=findViewById(R.id.etPassword);
        String userPassword = String.valueOf(etPassword.getText());

        //fazer verificaçao de dados pseudo e password
        UserBean u = new UserBean(userPseudo, userPassword);

        LogInActivity context=this; //a actividade onde estamos: loginactivity para o intent
        Thread thread = new Thread(new Runnable() { //async logo dentro de thread. Nao se pode fazer chamadas a net a partir da main thread sn bloqueia a aplicacao
            @Override
            public void run() {
                try {
                    if(WSUtils.verifyLogin(u)) { //se retornar true passamos par aa mainActivity
                        Intent intent = new Intent(context, MainActivity.class);

                        //p passar valor do pseudo do login a mainactivity = bundle
                        Bundle bundle = new Bundle();
                        bundle.putLong("id", u.getId());
                        bundle.putString("pseudo", u.getPseudo()); //chave q e pseudo + valor userPseudo e o que se usa depois e a chave (pseudo) e nao o seu valor
                        //bundle.putString("password", u.getPassword());
                        intent.putExtras(bundle);

                        startActivity(intent);
                    } else { //se retornar falso = error lança toast de aviso
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Password and/or pseudo invalids!!",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();




    }
} //fecha a class