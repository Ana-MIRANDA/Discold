package com.ana.discold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    private EditText etUserPseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }//fecha a oncreate

//ir p o chat depois de indicar pseudo
    public void goChat(View view) {

        etUserPseudo=findViewById(R.id.etUserPseudo);
        String userPseudo = String.valueOf(etUserPseudo.getText()); //nao poder ser so geText() pk por defeito e do tipo editable e eu quero String

        Intent intent = new Intent(this, MainActivity.class);

        //p passar valor do pseudo do login a mainactivity = bundle
        Bundle bundle = new Bundle();
        bundle.putString("pseudo", userPseudo); //chave q e pseudo + valor userPseudo
        intent.putExtras(bundle);

        startActivity(intent);
    }
} //fecha a class