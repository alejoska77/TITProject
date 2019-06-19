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

public class QuintoActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_quinto);
        final String fraseimportada="PREGUNTA 5";
        final String pregunta= "Que haría si alguien lo invita a salir";
        final Intent intent = getIntent();
//        final String username= intent.getStringExtra(Ruleta.usernames);
        final String username= intent.getStringExtra(TercerActivitie.user);

        Bundle bundle = this.getIntent().getExtras();
        String suma= bundle.getString("puntu");
        final int tota = Integer.parseInt(suma);

        radioGroup = (RadioGroup) findViewById(R.id.rbgpreguntas5);

        radioopone = (RadioButton) findViewById(R.id.radioButton52);
        radiooptwo = (RadioButton) findViewById(R.id.radioButton53);
        radioopThree = (RadioButton) findViewById(R.id.radioButton54);

        num = (TextView)findViewById(R.id.txtQuinto);

        //    String fraseimportada=bundle.getString("numero");
        num.setText(fraseimportada);
        inicializarFirebase();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                final int selectedId =  radioGroup.getCheckedRadioButtonId();

                switch (selectedId){

                    case R.id.radioButton52:

                        Persona p = new Persona();

                        Preguntas r = new Preguntas();

                        r.setId(username);
                        r.setDescripcion("Lo haría sin preocupación");
                        r.setPuntuacion("5");
                        r.setUser(fraseimportada);
                        int totalPunto = tota+(Integer.parseInt(r.getPuntuacion()));
                        r.setPregunta(pregunta);

                        r.setTotal(totalPunto);
                        databaseReference.child("Test").child(r.getId()).child(r.getUser()).setValue(r);
                        // Intent intent= new Intent(QuintoActivity.this, Ruleta.class);
                        Intent intent= new Intent(QuintoActivity.this, CuartoActivity.class);
                        intent.putExtra(user, r.getId());
                        Bundle b = new Bundle();
                        b.putString("puntu", String.valueOf(r.getTotal()));
                        intent.putExtras(b);
                        startActivity(intent);

                        break;
                    case R.id.radioButton53:

                        Persona pp = new Persona();
                        Preguntas rr = new Preguntas();
                        rr.setId(username);
                        rr.setDescripcion("Lo dudaría para que no malinterpeten mis sentimientos");
                        rr.setPuntuacion("3");
                        rr.setUser(fraseimportada);

                        int totalPunto2 = tota+(Integer.parseInt(rr.getPuntuacion()));
                        rr.setPregunta(pregunta);

                        rr.setTotal(totalPunto2);
                        databaseReference.child("Test").child(rr.getId()).child(rr.getUser()).setValue(rr);
                        // Intent intent2= new Intent(QuintoActivity.this, Ruleta.class);

                        Intent intent2= new Intent(QuintoActivity.this, CuartoActivity.class);
                        intent2.putExtra(user, rr.getId());
                        Bundle bb = new Bundle();
                        bb.putString("puntu", String.valueOf(rr.getTotal()));
                        intent2.putExtras(bb);
                        startActivity(intent2);
                        break;
                    case R.id.radioButton54:

                        Persona pep = new Persona();
                        Preguntas rer = new Preguntas();
                        rer.setId(username);
                        rer.setDescripcion("Tendría desconfianza y le diría que no");
                        rer.setPuntuacion("1");
                        rer.setUser(fraseimportada);

                        int totalPunto3 = tota+(Integer.parseInt(rer.getPuntuacion()));
                        rer.setPregunta(pregunta);

                        rer.setTotal(totalPunto3);

                        databaseReference.child("Test").child(rer.getId()).child(rer.getUser()).setValue(rer);
                        // Intent intent3= new Intent(QuintoActivity.this, Ruleta.class);

                        Intent intent3= new Intent(QuintoActivity.this, CuartoActivity.class);
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

        Intent intent= new Intent(QuintoActivity.this, SegundoActivity.class);
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
