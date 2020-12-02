package com.ana.discold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ana.discold.Beans.UserBean;
import com.ana.discold.WSUtils.WSUtils;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    private EditText etUserPseudo;
    private EditText etPassword;




    //ir p o chat depois de indicar pseudo
    public void goRegister(View view) {

        etUserPseudo=findViewById(R.id.etUserPseudo);
        String userPseudo = String.valueOf(etUserPseudo.getText()); //nao poder ser so geText() pk por defeito e do tipo editable e eu quero String

        etPassword=findViewById(R.id.etPassword);
        String userPassword = String.valueOf(etPassword.getText());

        //le serveur recoit pseudo et pasw
        final UserBean u = new UserBean(userPseudo, userPassword);


        Thread thread = new Thread(new Runnable() { //async logo dentro de thread. Nao se pode fazer chamadas a net a partir da main thread sn bloqueia a aplicacao
            @Override
            public void run() {
                try {
                    UserBean user = WSUtils.register(u);
                    if( user != null ) { // si true on passe a la MainActivity
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        intent.putExtra("user", user); //para passar o id de u fizemos serializable ao objejto em vez de bundle.putExtra(id) e assim para pseudo e passwprd
                        startActivity(intent);
                    } else { //si erreur
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "Password and/or pseudo invalids!!",
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
}