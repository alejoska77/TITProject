package com.example.irea.pruebacrud;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.irea.pruebacrud.model.Preguntas;
import com.google.firebase.auth.FirebaseAuth;

public class activity_final extends AppCompatActivity implements View.OnClickListener{



    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        Button mBtnsalir = findViewById(R.id.btnSalir);
        firebaseAuth= FirebaseAuth.getInstance();
        mBtnsalir.setOnClickListener(this);
        }



    @Override
    public void onClick(View v) {
        Intent intent = getIntent();
        //final String username= intent.getStringExtra(Ruleta.usernames);


        switch (v.getId())
        {


            case R.id.btnSalir:
                firebaseAuth.signOut();//Permite salir al usuario es decir desloguearse
                finish();
                startActivity(new Intent(this, LoginActivity1.class));

        }
    }
}


