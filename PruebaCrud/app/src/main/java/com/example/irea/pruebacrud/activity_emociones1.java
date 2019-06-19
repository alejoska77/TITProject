package com.example.irea.pruebacrud;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.irea.pruebacrud.model.Emociones;
import com.example.irea.pruebacrud.model.Persona;
import com.example.irea.pruebacrud.model.Preguntas;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_emociones1 extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseUser mCurrentuser;
    private MediaPlayer respuestCorrecta;
    private MediaPlayer respuestaIncorrecta;
    private FirebaseAuth mauth;
    public static final String total = "user";
    public static final int operar =0;
    public static final String user = "user";

   // TextView num;
    private ImageView imgCorrecto;
    private ImageView imgIncorrecto;
    private ImageView imageViewres1p1;
    private ImageView imageViewres1p2;
    private TextView txtEspera;
    private MediaPlayer nivel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emociones1);
        final int totalPunt = 0;

        Intent intent = getIntent();
        //final String username= intent.getStringExtra(Ruleta.usernames);
        final String username= intent.getStringExtra(SextoActivity.user);

        // String userid= intent.getStringExtra(Ruleta.userid);
        final String fraseimportada="PREGUNTA 1";

        final String pregunta="En que situación sentirias Felicidad";
        txtEspera= findViewById(R.id.txtEspera);
        nivel2= MediaPlayer.create(this, R.raw.nivel2);

        imgCorrecto=findViewById(R.id.imgCorrectoem1);
        respuestCorrecta= MediaPlayer.create(this, R.raw.correcto);
        respuestaIncorrecta= MediaPlayer.create(this, R.raw.losientotehasequivocao);

        imgIncorrecto=findViewById(R.id.imginCorrectoem1);
        imageViewres1p1=findViewById(R.id.imageViewres1p1);
        imageViewres1p2=findViewById(R.id.imageViewres1p2);


      //  num = (TextView)findViewById(R.id.txtPrimero);
        //Bundle bundle = getIntent().getExtras();
        //    String fraseimportada=bundle.getString("numero");
     //   num.setText(fraseimportada);
        inicializarFirebase();
         inicioJuego();

        imageViewres1p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgCorrecto();
                Persona p = new Persona();

                Emociones r = new Emociones();


                r.setId(username);
                r.setDescripcion("Correcto");
                r.setPuntuacion("1");
                r.setUser(fraseimportada);
                int totalPunto=totalPunt +(Integer.parseInt(r.getPuntuacion()));
                r.setTotal(totalPunto);
                r.setPregunta(pregunta);

                databaseReference.child("Emociones").child(r.getId()).child(r.getUser()).setValue(r);
                //Intent intent= new Intent(PrimerActivity.this, Ruleta.class);
                Intent intent= new Intent(activity_emociones1.this, activity_emociones2.class);
                intent.putExtra(user, r.getId());
                Bundle b = new Bundle();
                b.putString("puntu", String.valueOf(r.getTotal()));
                intent.putExtras(b);
                startActivity(intent);
                nivel2.stop();



            }
        });

        imageViewres1p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imginCorrecto();
                Persona p = new Persona();

                Emociones r = new Emociones();


                r.setId(username);
                r.setDescripcion("Incorrecto");
                r.setPuntuacion("0");
                r.setUser(fraseimportada);
                int totalPunto=totalPunt +(Integer.parseInt(r.getPuntuacion()));
                r.setTotal(totalPunto);
                r.setPregunta(pregunta);

                databaseReference.child("Emociones").child(r.getId()).child(r.getUser()).setValue(r);
                //Intent intent= new Intent(PrimerActivity.this, Ruleta.class);
                Intent intent= new Intent(activity_emociones1.this, activity_emociones2.class);
                intent.putExtra(user, r.getId());
                Bundle b = new Bundle();
                b.putString("puntu", String.valueOf(r.getTotal()));
                intent.putExtras(b);
                startActivity(intent);
                nivel2.stop();



            }
        });

    }

    //  public void onClickRetornar(View v)
    // {
    // Intent i = new Intent(  PrimerActivity.this, Ruleta.class );
    //   startActivity( i );
    // }


/*        public void onClick(View v) {

            Intent intent= new Intent(PrimerActivity.this, SegundoActivity.class);
            startActivity(intent);
        }
*/

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        // firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }
    void imgCorrecto(){
        new CountDownTimer(1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtEspera.setText(""+(millisUntilFinished/1000)+1);
                imageViewres1p1.setVisibility(View.INVISIBLE);
                imageViewres1p2.setVisibility(View.INVISIBLE);
                imgCorrecto.setVisibility(View.VISIBLE);


            }

            @Override
            public void onFinish() {

                respuestCorrecta.start();
                imgCorrecto.setVisibility(View.INVISIBLE);
                imageViewres1p1.setVisibility(View.VISIBLE);
                imageViewres1p2.setVisibility(View.VISIBLE);


            }
        }.start();


    }
    void imginCorrecto(){
        new CountDownTimer(1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtEspera.setText(""+(millisUntilFinished/1000)+1);
                imageViewres1p1.setVisibility(View.INVISIBLE);
                imageViewres1p2.setVisibility(View.INVISIBLE);
                imgIncorrecto.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                imgIncorrecto.setVisibility(View.INVISIBLE);
                imageViewres1p1.setVisibility(View.VISIBLE);
                imageViewres1p2.setVisibility(View.VISIBLE);
                respuestaIncorrecta.start();
                //respuestCorrecta.start();

            }
        }.start();


    }
    void inicioJuego(){
        new CountDownTimer(1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtEspera.setText(""+(millisUntilFinished/1000)+1);
                nivel2.start();

            }

            @Override
            public void onFinish() {

            }
        }.start();

    }


}

