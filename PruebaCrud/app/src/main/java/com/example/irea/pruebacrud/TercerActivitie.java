package com.example.irea.pruebacrud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.irea.pruebacrud.model.Persona;
import com.example.irea.pruebacrud.model.Preguntas;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Integer.parseInt;


public class TercerActivitie extends AppCompatActivity {
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
        setContentView(R.layout.activity_tercero);
        final String fraseimportada="PREGUNTA 3";

        final String pregunta= "Que haría si, su maestro le pide pasar a la pizarra";
        final Intent intent = getIntent();
        //final String username= intent.getStringExtra(Ruleta.usernames);
        final String username= intent.getStringExtra(SegundoActivity.user);

        Bundle bundle = this.getIntent().getExtras();
        String suma= bundle.getString("puntu");
        final int tota = Integer.parseInt(suma);

        radioGroup = (RadioGroup) findViewById(R.id.rbgpreguntas3);

        radioopone = (RadioButton) findViewById(R.id.radioButton32);
        radiooptwo = (RadioButton) findViewById(R.id.radioButton33);
        radioopThree = (RadioButton) findViewById(R.id.radioButton34);

        num = (TextView)findViewById(R.id.txtTercero);

        //    String fraseimportada=bundle.getString("numero");
        num.setText(fraseimportada);
        inicializarFirebase();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                final int selectedId =  radioGroup.getCheckedRadioButtonId();

                switch (selectedId){

                    case R.id.radioButton32:

                        Persona p = new Persona();

                        Preguntas r = new Preguntas();

                        r.setId(username);
                        r.setDescripcion("Yo deseo pasar");
                        r.setPuntuacion("5");
                        //r.puntajeto[3]=5;
                        r.setUser(fraseimportada);

                        int totalPunto = tota+(Integer.parseInt(r.getPuntuacion()));
                        r.setPregunta(pregunta);

                        r.setTotal(totalPunto);

                        databaseReference.child("Test").child(r.getId()).child(r.getUser()).setValue(r);
                       // Intent intent= new Intent(TercerActivitie.this, Ruleta.class);
                       Intent intent= new Intent(TercerActivitie.this, QuintoActivity.class);
                        intent.putExtra(user, r.getId());
                        Bundle b = new Bundle();
                        b.putString("puntu", String.valueOf(r.getTotal()));
                        intent.putExtras(b);

                        startActivity(intent);

                        break;
                    case R.id.radioButton33:

                        Persona pp = new Persona();
                        Preguntas rr = new Preguntas();
                        rr.setId(username);
                        rr.setDescripcion("Me van a criticar si paso");
                        rr.setPuntuacion("3");
                    //    rr.puntajeto[3]=3;
                        rr.setUser(fraseimportada);

                        int totalPunto2 = tota+(Integer.parseInt(rr.getPuntuacion()));
                        rr.setPregunta(pregunta);

                        rr.setTotal(totalPunto2);
                        databaseReference.child("Test").child(rr.getId()).child(rr.getUser()).setValue(rr);
                        //Intent intent2= new Intent(TercerActivitie.this, Ruleta.class);
                        Intent intent2= new Intent(TercerActivitie.this, QuintoActivity.class);
                        intent2.putExtra(user, rr.getId());
                        Bundle bb = new Bundle();
                        bb.putString("puntu", String.valueOf(rr.getTotal()));
                        intent2.putExtras(bb);
                        startActivity(intent2);
                        break;
                    case R.id.radioButton34:

                        Persona pep = new Persona();
                        Preguntas rer = new Preguntas();
                        rer.setId(username);
                        rer.setDescripcion("Ojalá no me selecione");
                        rer.setPuntuacion("1");
                        //rer.puntajeto[3]=1;
                        rer.setUser(fraseimportada);

                        int totalPunto3 = tota+(Integer.parseInt(rer.getPuntuacion()));
                        rer.setPregunta(pregunta);

                        rer.setTotal(totalPunto3);

                        databaseReference.child("Test").child(rer.getId()).child(rer.getUser()).setValue(rer);
                        //Intent intent3= new Intent(TercerActivitie.this, Ruleta.class);
                        Intent intent3= new Intent(TercerActivitie.this, QuintoActivity.class);
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

        Intent intent= new Intent(TercerActivitie.this, SegundoActivity.class);
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
