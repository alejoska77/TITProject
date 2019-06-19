package com.example.irea.pruebacrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.irea.pruebacrud.model.Persona;
import com.example.irea.pruebacrud.model.Preguntas;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SegundoActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private RadioGroup radioGroup;
    private RadioButton radioopone;
    private RadioButton radiooptwo;
    private RadioButton radioopThree;
    public static final String total = "total";
    public static final String user = "user";

    TextView num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);
        final String fraseimportada="PREGUNTA 2";
        final String pregunta="Que haría si, sus amigos le dicen que pida permiso para salir con ellos";
        final Intent intent = getIntent();
       // final String username= intent.getStringExtra(Ruleta.usernames);
        //String userid= intent.getStringExtra(Ruleta.userid);
       final String username= intent.getStringExtra(PrimerActivity.user);

       Bundle bundle = this.getIntent().getExtras();
       String suma= bundle.getString("puntu");
       final int tota = Integer.parseInt(suma);
        //final String totalpun= (intent.getStringExtra(PrimerActivity.total));

        radioGroup = (RadioGroup) findViewById(R.id.rbgpreguntas2);

        radioopone = (RadioButton) findViewById(R.id.radioButton22);
        radiooptwo = (RadioButton) findViewById(R.id.radioButton23);
        radioopThree = (RadioButton) findViewById(R.id.radioButton24);

        num = (TextView)findViewById(R.id.txtSegundo);

        //    String fraseimportada=bundle.getString("numero");
        num.setText(fraseimportada);
        inicializarFirebase();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                final int selectedId =  radioGroup.getCheckedRadioButtonId();

                switch (selectedId){

                    case R.id.radioButton22:

                        Persona p = new Persona();

                        Preguntas r = new Preguntas();

                        r.setId(username);
                        r.setDescripcion("Se que me darán permiso");
                        r.setPuntuacion("5");
                        r.setUser(fraseimportada);
                      //  int totalPunto= parseInt(r.getPuntuacion());
                        
                        int totalPunto = tota+(Integer.parseInt(r.getPuntuacion()));
                        r.setPregunta(pregunta);

                        r.setTotal(totalPunto);
                        databaseReference.child("Test").child(r.getId()).child(r.getUser()).setValue(r);
                       // Intent intent= new Intent(SegundoActivity.this, Ruleta.class);
                       Intent intent= new Intent(SegundoActivity.this, TercerActivitie.class);
                        intent.putExtra(user, r.getId());
                        Bundle b = new Bundle();
                        b.putString("puntu", String.valueOf(r.getTotal()));
                        intent.putExtras(b);

                        startActivity(intent);

                        break;
                    case R.id.radioButton23:

                        Persona pp = new Persona();
                        Preguntas rr = new Preguntas();
                        rr.setId(username);
                        rr.setDescripcion("Tal vez los convenza");
                        rr.setPuntuacion("3");
                        rr.setUser(fraseimportada);
                        rr.setPregunta(pregunta);
                        int totalPunto2 = tota+(Integer.parseInt(rr.getPuntuacion()));

                        rr.setTotal(totalPunto2);
                        databaseReference.child("Test").child(rr.getId()).child(rr.getUser()).setValue(rr);
                        //Intent intent2= new Intent(SegundoActivity.this, Ruleta.class);
                        Intent intent2= new Intent(SegundoActivity.this, TercerActivitie.class);
                        intent2.putExtra(user, rr.getId());
                        Bundle bb = new Bundle();
                        bb.putString("puntu", String.valueOf(rr.getTotal()));
                        intent2.putExtras(bb);

                        startActivity(intent2);
                        break;
                    case R.id.radioButton24:

                        Persona pep = new Persona();
                        Preguntas rer = new Preguntas();
                        rer.setId(username);
                        rer.setDescripcion("Sé que no me darán permiso");
                        rer.setPuntuacion("1");
                      //  rer.puntajeto[2]=1;
                        rer.setUser(fraseimportada);
                        rer.setPregunta(pregunta);
                        int totalPunto3 = tota+(Integer.parseInt(rer.getPuntuacion()));

                        rer.setTotal(totalPunto3);

                        databaseReference.child("Test").child(rer.getId()).child(rer.getUser()).setValue(rer);
                        //Intent intent3= new Intent(SegundoActivity.this, Ruleta.class);
                        Intent intent3= new Intent(SegundoActivity.this, TercerActivitie.class);
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


    public void onClick(View v) {

        Intent intent= new Intent(SegundoActivity.this, SegundoActivity.class);
        startActivity(intent);
    }


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


}
