package com.example.irea.pruebacrud;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.irea.pruebacrud.model.Emociones;
import com.example.irea.pruebacrud.model.Persona;
import com.example.irea.pruebacrud.model.Preguntas;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_emociones5 extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseUser mCurrentuser;

    private FirebaseAuth mauth;
    public static final String total = "user";
    public static final int operar =0;
    public static final String user = "user";

    private ImageView imgCorrecto;
    private ImageView imgIncorrecto;
    private MediaPlayer respuestCorrecta;
    private MediaPlayer respuestaIncorrecta;
    private ImageView imageViewres2p1;
    private ImageView imageViewres2p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emociones5);

        final     Intent intent = getIntent();
        //final String username= intent.getStringExtra(Ruleta.usernames);
        final String username= intent.getStringExtra(activity_emociones4.user);
        // final String username= intent.getStringExtra(Ruleta.usernames);
        //  String userid= intent.getStringExtra(Ruleta.userid);

        Bundle bundle = this.getIntent().getExtras();
        String suma= bundle.getString("puntu");
        final int tota = Integer.parseInt(suma);
        // String userid= intent.getStringExtra(Ruleta.userid);
        final String fraseimportada="PREGUNTA 5";

        final String pregunta="En que situación sentirias Tristeza";

        respuestCorrecta= MediaPlayer.create(this, R.raw.correcto);
        respuestaIncorrecta= MediaPlayer.create(this, R.raw.losientotehasequivocao);
        imgCorrecto=findViewById(R.id.imgCorrectoem5);

        imgIncorrecto=findViewById(R.id.imginCorrectoem5);
        imageViewres2p1=findViewById(R.id.imageViewres5p1);
        imageViewres2p2=findViewById(R.id.imageViewres5p2);



        //    String fraseimportada=bundle.getString("numero");
        inicializarFirebase();

        imageViewres2p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgCorrecto();
                Persona p = new Persona();

                Emociones r = new Emociones();


                r.setId(username);
                r.setDescripcion("Correcto");
                r.setPuntuacion("1");
                r.setUser(fraseimportada);
                int totalPunto=tota +(Integer.parseInt(r.getPuntuacion()));
                r.setTotal(totalPunto);
                r.setPregunta(pregunta);

                databaseReference.child("Emociones").child(r.getId()).child(r.getUser()).setValue(r);
                //Intent intent= new Intent(PrimerActivity.this, Ruleta.class);
                Intent intent= new Intent(activity_emociones5.this, activity_emociones6.class);
                intent.putExtra(user, r.getId());
                Bundle b = new Bundle();
                b.putString("puntu", String.valueOf(r.getTotal()));
                intent.putExtras(b);
                startActivity(intent);



            }
        });

        imageViewres2p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imginCorrecto();
                Persona p = new Persona();

                Emociones r = new Emociones();


                r.setId(username);
                r.setDescripcion("Incorrecto");
                r.setPuntuacion("0");
                r.setUser(fraseimportada);
                int totalPunto=tota +(Integer.parseInt(r.getPuntuacion()));
                r.setTotal(totalPunto);
                r.setPregunta(pregunta);

                databaseReference.child("Emociones").child(r.getId()).child(r.getUser()).setValue(r);
                //Intent intent= new Intent(PrimerActivity.this, Ruleta.class);
                Intent intent= new Intent(activity_emociones5.this, activity_emociones6.class);
                intent.putExtra(user, r.getId());
                Bundle b = new Bundle();
                b.putString("puntu", String.valueOf(r.getTotal()));
                intent.putExtras(b);
                startActivity(intent);



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
                //txtEspera.setText(""+(millisUntilFinished/1000)+1);
                imageViewres2p1.setVisibility(View.INVISIBLE);
                imageViewres2p2.setVisibility(View.INVISIBLE);
                imgCorrecto.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {

                imgCorrecto.setVisibility(View.INVISIBLE);
                imageViewres2p1.setVisibility(View.VISIBLE);
                imageViewres2p2.setVisibility(View.VISIBLE);

                respuestCorrecta.start();

            }
        }.start();


    }
    void imginCorrecto(){
        new CountDownTimer(1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                //txtEspera.setText(""+(millisUntilFinished/1000)+1);
                imageViewres2p1.setVisibility(View.INVISIBLE);
                imageViewres2p2.setVisibility(View.INVISIBLE);

                imgIncorrecto.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                imgIncorrecto.setVisibility(View.INVISIBLE);

                imageViewres2p1.setVisibility(View.VISIBLE);
                imageViewres2p2.setVisibility(View.VISIBLE);

                respuestaIncorrecta.start();

            }
        }.start();


    }


}

