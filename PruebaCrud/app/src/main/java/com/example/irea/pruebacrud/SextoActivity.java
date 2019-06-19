package com.example.irea.pruebacrud;

import android.appwidget.AppWidgetProvider;
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

public class SextoActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_sexto);
        final String fraseimportada="PREGUNTA 6";
        final String pregunta =" Que haría si, felicitan a alguien por ser el mejor";
        final Intent intent = getIntent();
//        final String username= intent.getStringExtra(Ruleta.usernames);
        final String username= intent.getStringExtra(QuintoActivity.user);

        Bundle bundle = this.getIntent().getExtras();
        String suma= bundle.getString("puntu");
        final int tota = Integer.parseInt(suma);

        radioGroup = (RadioGroup) findViewById(R.id.rbgpreguntas6);

        radioopone = (RadioButton) findViewById(R.id.radioButton62);
        radiooptwo = (RadioButton) findViewById(R.id.radioButton63);
        radioopThree = (RadioButton) findViewById(R.id.radioButton64);

        num = (TextView)findViewById(R.id.txtSexto);
        //    String fraseimportada=bundle.getString("numero");
        num.setText(fraseimportada);
        inicializarFirebase();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                final int selectedId =  radioGroup.getCheckedRadioButtonId();

                switch (selectedId){

                    case R.id.radioButton62:

                        Persona p = new Persona();

                        Preguntas r = new Preguntas();

                        r.setId(username);
                        r.setDescripcion("Lo felicitaría");
                        r.setPuntuacion("5");
                        r.setUser(fraseimportada);
                        int totalPunto = tota+(Integer.parseInt(r.getPuntuacion()));
                        r.setPregunta(pregunta);

                        r.setTotal(totalPunto);
                        databaseReference.child("Test").child(r.getId()).child(r.getUser()).setValue(r);
                        Preguntas s = new Preguntas();
                        s.setId(username);
                        s.setTotal(totalPunto);

                        databaseReference.child("Resultados").child(s.getId()).setValue(s);
                        databaseReference.child("Tratamiento").child(s.getId()).setValue(s);
                        // Intent intent= new Intent(SextoActivity.this, Ruleta.class);

                        Intent intent= new Intent(SextoActivity.this, activity_emociones1.class);
                        intent.putExtra(user, r.getId());

                        // intent.putExtra(user, r.getId());
                        //Bundle b = new Bundle();
                        //b.putString("puntu", String.valueOf(0));
                        //intent.putExtras(b);
                        startActivity(intent);

                        break;
                    case R.id.radioButton63:

                        Persona pp = new Persona();
                        Preguntas rr = new Preguntas();
                        rr.setId(username);
                        rr.setDescripcion("No me importaría");
                        rr.setPuntuacion("3");
                        rr.setUser(fraseimportada);

                        int totalPunto2 = tota+(Integer.parseInt(rr.getPuntuacion()));
                        rr.setPregunta(pregunta);

                        rr.setTotal(totalPunto2);
                        databaseReference.child("Test").child(rr.getId()).child(rr.getUser()).setValue(rr);
                        Preguntas ss = new Preguntas();
                        ss.setId(username);
                        ss.setTotal(totalPunto2);

                        databaseReference.child("Resultados").child(ss.getId()).setValue(ss);
                        databaseReference.child("Tratamiento").child(ss.getId()).setValue(ss);
                        // Intent intent2= new Intent(SextoActivity.this, Ruleta.class);

                        Intent intent2= new Intent(SextoActivity.this, activity_emociones1.class);
                        intent2.putExtra(user, rr.getId());
                        Bundle bb = new Bundle();
                        bb.putString("puntu", String.valueOf(0));
                        intent2.putExtras(bb);
                        startActivity(intent2);
                        break;
                    case R.id.radioButton64:

                        Persona pep = new Persona();
                        Preguntas rer = new Preguntas();
                        rer.setId(username);
                        rer.setDescripcion("Me Molestaría");
                        rer.setPuntuacion("1");
                        rer.setUser(fraseimportada);

                        int totalPunto3 = tota+(Integer.parseInt(rer.getPuntuacion()));
                        rer.setPregunta(pregunta);

                        rer.setTotal(totalPunto3);

                        databaseReference.child("Test").child(rer.getId()).child(rer.getUser()).setValue(rer);
                        Preguntas sss = new Preguntas();
                        sss.setId(username);
                        sss.setTotal(totalPunto3);

                        databaseReference.child("Resultados").child(sss.getId()).setValue(sss);
                        databaseReference.child("Tratamiento").child(sss.getId()).setValue(sss);
                        // Intent intent3= new Intent(SextoActivity.this, Ruleta.class);

                        Intent intent3= new Intent(SextoActivity.this, activity_emociones1.class);
                        intent3.putExtra(user, rer.getId());
                        Bundle bbb = new Bundle();
                        bbb.putString("puntu", String.valueOf(0));
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

        Intent intent= new Intent(SextoActivity.this, SegundoActivity.class);
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
