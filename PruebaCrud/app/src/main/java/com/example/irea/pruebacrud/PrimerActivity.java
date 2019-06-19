package com.example.irea.pruebacrud;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.irea.pruebacrud.model.Persona;
import com.example.irea.pruebacrud.model.Preguntas;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PrimerActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseUser mCurrentuser;
    private RadioGroup radioGroup;
    private RadioButton radioopone;
    private RadioButton radiooptwo;
    private RadioButton radioopThree;
    private FirebaseAuth mauth;
    public static final String total = "user";
    public static final int operar =0;
    public static final String user = "user";

    TextView num;
    private ImageView imgCorrecto;
    private MediaPlayer nivel1;
    private TextView txtEspera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer);
        final int totalPunt = 0;

        Intent intent = getIntent();
        //final String username= intent.getStringExtra(Ruleta.usernames);
        final String username= intent.getStringExtra(activity_main_use.user);

       // String userid= intent.getStringExtra(Ruleta.userid);
        final String fraseimportada="PREGUNTA 1";

        final String pregunta="Que haría si su madre necesita que le dones un organo";
        txtEspera= findViewById(R.id.txtEspera3);

        nivel1= MediaPlayer.create(this, R.raw.nivel1);
        radioGroup = (RadioGroup) findViewById(R.id.rbgpreguntas);
        imgCorrecto=findViewById(R.id.imgCorrecto2);

        radioopone = (RadioButton) findViewById(R.id.radioButton);
        radiooptwo = (RadioButton) findViewById(R.id.radioButton2);
        radioopThree = (RadioButton) findViewById(R.id.radioButton3);

        num = (TextView)findViewById(R.id.txtPrimero);
        Bundle bundle = getIntent().getExtras();
    //    String fraseimportada=bundle.getString("numero");
        num.setText(fraseimportada);
        inicializarFirebase();
        inicioJuego();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                final int selectedId =  radioGroup.getCheckedRadioButtonId();

                switch (selectedId){

                    case R.id.radioButton:

                        Persona p = new Persona();

                        Preguntas r = new Preguntas();


                        r.setId(username);
                        r.setDescripcion("Lo donaría sin pensarlo");
                        r.setPuntuacion("5");
                        r.setUser(fraseimportada);
                        int totalPunto=totalPunt +(Integer.parseInt(r.getPuntuacion()));
                        r.setTotal(totalPunto);
                        r.setPregunta(pregunta);

                        databaseReference.child("Test").child(r.getId()).child(r.getUser()).setValue(r);
                        //Intent intent= new Intent(PrimerActivity.this, Ruleta.class);
                        Intent intent= new Intent(PrimerActivity.this, SegundoActivity.class);
                        nivel1.stop();
                        intent.putExtra(user, r.getId());
                        Bundle b = new Bundle();
                        b.putString("puntu", String.valueOf(r.getTotal()));
                        intent.putExtras(b);
                        startActivity(intent);

                        break;
                    case R.id.radioButton2:

                        Persona pp = new Persona();
                        Preguntas rr = new Preguntas();
                        rr.setId(username);
                        rr.setDescripcion("Tal vez lo haga, pensando primero en las consecuencias para mi cuerpo");
                        rr.setPuntuacion("3");
                        //rr.puntajeto[1]=3;
                        rr.setUser(fraseimportada);
                        int totalPunto2=totalPunt +(Integer.parseInt(rr.getPuntuacion()));
                        rr.setTotal(totalPunto2);
                        rr.setPregunta(pregunta);

                        databaseReference.child("Test").child(rr.getId()).child(rr.getUser()).setValue(rr);
//                        Intent intent2= new Intent(PrimerActivity.this, Ruleta.class);
                        Intent intent2= new Intent(PrimerActivity.this, SegundoActivity.class);
                        nivel1.stop();
                        intent2.putExtra(user, rr.getId());
                        Bundle bb = new Bundle();
                        bb.putString("puntu", String.valueOf(rr.getTotal()));
                        intent2.putExtras(bb);


                        startActivity(intent2);

                        break;
                    case R.id.radioButton3:

                        Persona pep = new Persona();
                        Preguntas rer = new Preguntas();
                        rer.setId(username);
                        rer.setDescripcion("No lo haría porque lo necesito para tener una vida normal");
                        rer.setPuntuacion("1");
                        //rer.puntajeto[1]=1;
                        rer.setUser(fraseimportada);
                        int totalPunto3=totalPunt +(Integer.parseInt(rer.getPuntuacion()));
                        rer.setTotal(totalPunto3);
                        rer.setPregunta(pregunta);

                        databaseReference.child("Test").child(rer.getId()).child(rer.getUser()).setValue(rer);
                       // Intent intent3= new Intent(PrimerActivity.this, Ruleta.class);
                        Intent intent3= new Intent(PrimerActivity.this, SegundoActivity.class);
                        nivel1.stop();
                        intent3.putExtra(user, rer.getId());
                        Bundle bbb = new Bundle();
                        bbb.putString("puntu", String.valueOf(rer.getTotal()));
                        intent3.putExtras(bbb);

                        startActivity(intent3);

                        break;

                }
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
    public void addListenerOnButton(final String ruleta) {

        final String Idruleta=ruleta;


        final int selectedId =  radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id



    }
    void imgCorrecto(){
        new CountDownTimer(1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                //txtEspera.setText(""+(millisUntilFinished/1000)+1);
                imgCorrecto.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                imgCorrecto.setVisibility(View.INVISIBLE);
                //respuestCorrecta.start();

            }
        }.start();


    }
    void inicioJuego(){
        new CountDownTimer(1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtEspera.setText(""+(millisUntilFinished/1000)+1);
                nivel1.start();

            }

            @Override
            public void onFinish() {

            }
        }.start();

    }



}
