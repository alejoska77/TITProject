package com.example.irea.pruebacrud;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.irea.pruebacrud.model.Emociones;
import com.example.irea.pruebacrud.model.Preguntas;



public class activity_main_use extends AppCompatActivity implements View.OnClickListener {
    public static final String user = "user";
    TextView num;
    private TextView txtEspera;

    private ImageView imgayuda;
    private MediaPlayer bienvenida;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        Intent intent = getIntent();
        //final String username= intent.getStringExtra(Ruleta.usernames);
        final String username= intent.getStringExtra(LoginActivity1.username);
        num = (TextView)findViewById(R.id.welcomw);
        txtEspera= findViewById(R.id.txtEspera2);
        bienvenida= MediaPlayer.create(this, R.raw.bienvenidasonido);
        Button mBtntest = findViewById(R.id.btntest);
        imgayuda=findViewById(R.id.imgayuda);
        mBtntest.setOnClickListener(this);
        Button mBtnEmociones = findViewById(R.id.btnemociones);
        mBtnEmociones.setOnClickListener(this);
        inicioJuego();

        imgayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder ayuda = new AlertDialog.Builder(activity_main_use.this);
                ayuda.setMessage("El juego consiste en 2 areas :" +"\n" +"\n"+ "-TEST: que permite identificar tu estado de animo. "
                        +"\n"+ "\n"+ "-RECONOCE TUS EMOCIONES: que permite identificar si tienes control sobre tus emociones.").setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog titulo = ayuda.create();
                titulo.setTitle("Ayuda");
                titulo.show();

            }
        });
    }



    @Override
    public void onClick(View v) {
        Intent intent = getIntent();
        //final String username= intent.getStringExtra(Ruleta.usernames);
        final String username= intent.getStringExtra(LoginActivity1.username);

        switch (v.getId())
        {


            case R.id.btntest:
                Preguntas r = new Preguntas();

                r.setId(username);
                Intent intent2= new Intent(activity_main_use.this, PrimerActivity.class);
                intent2.putExtra(user, r.getId());
                startActivity(intent2);
                bienvenida.stop();
                break;
            case R.id.btnemociones:
                Preguntas s = new Preguntas();

                s.setId(username);
                Intent intent3= new Intent(activity_main_use.this, activity_emociones1.class);
                intent3.putExtra(user, s.getId());
                startActivity(intent3);
                bienvenida.stop();
            }
    }
    void inicioJuego(){
        new CountDownTimer(1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtEspera.setText(""+(millisUntilFinished/1000)+1);
                bienvenida.start();

            }

            @Override
            public void onFinish() {

            }
        }.start();

    }
}


