package com.example.irea.pruebacrud;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.irea.pruebacrud.Presentador.PresentadorRegistro;
import com.example.irea.pruebacrud.model.Rol;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VistaRegistro extends AppCompatActivity implements View.OnClickListener {

    private EditText txt_nombrePersona, txt_username, txt_email, txt_passwordPersona;
    private PresentadorRegistro presentadorRegistro;

    private List<Rol> listrol = new ArrayList<Rol>();
    ArrayAdapter<Rol> arrayAdapterrol;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    Spinner comboRoles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_registro);



        FirebaseAuth mAuth= FirebaseAuth.getInstance();
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        presentadorRegistro= new PresentadorRegistro(this, mAuth, mDatabase);

        txt_nombrePersona=findViewById(R.id.etnombreUsuario);
        txt_username= findViewById(R.id.etUsername);
        txt_email=findViewById(R.id.etEmail);
        txt_passwordPersona=findViewById(R.id.etPassword);
        comboRoles=(Spinner) findViewById(R.id.comboRoles);
        inicializarFirebase();
        listarDatosRol();


        Button mRegisterBtn= findViewById(R.id.btnRegistro);
        mRegisterBtn.setOnClickListener(this);
    }


    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        // firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    private void listarDatosRol() {

        databaseReference.child("Rol").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listrol.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Rol r= objSnaptshot.getValue(Rol.class);
                    listrol.add(r);


                    arrayAdapterrol= new ArrayAdapter<Rol>(VistaRegistro.this, android.R.layout.simple_spinner_item
                            , listrol);
                    comboRoles.setAdapter(arrayAdapterrol);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnRegistro:
                String nombre = txt_nombrePersona.getText().toString().trim();
                String usuario = txt_username.getText().toString().trim();
                String email = txt_email.getText().toString().trim();
                String pass = txt_passwordPersona.getText().toString().trim();
                String uid= UUID.randomUUID().toString();
                String rol = comboRoles.getSelectedItem().toString();

                presentadorRegistro.signUpUser(email, pass, nombre, usuario,uid, rol);
        }
    }
}
