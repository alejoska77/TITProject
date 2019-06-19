package com.example.irea.pruebacrud;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.irea.pruebacrud.Presentador.PresentadorRegistro;
import com.example.irea.pruebacrud.model.Persona;
import com.example.irea.pruebacrud.model.Rol;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Usuarios extends AppCompatActivity {

    private List<Persona> listPerson = new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapterpersona;

    EditText nomP, appP, correoP, passwordP;
    ListView listV_personas;

    private List<Rol> listrol = new ArrayList<Rol>();
    ArrayAdapter<Rol> arrayAdapterrol;


    Spinner combRoles;
    //private PresentadorRegistro presentadorRegistro;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth; //Definición de variable para registro de usuarios en firebase

    Persona personaSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        firebaseAuth= FirebaseAuth.getInstance();

        FirebaseAuth mAuth= FirebaseAuth.getInstance();
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
     //   presentadorRegistro= new PresentadorRegistro(this, mAuth, mDatabase);

        nomP = findViewById(R.id.txt_nombrePersona);
        appP = findViewById(R.id.txt_appPersona);
        correoP = findViewById(R.id.txt_correoPersona);
        passwordP = findViewById(R.id.txt_passwordPersona);

        combRoles=(Spinner) findViewById(R.id.combRoles);




        listV_personas = findViewById(R.id.lv_datosPersonas);
        inicializarFirebase();
        listarDatos();
        listarDatosRol();

        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSelected=(Persona) parent.getItemAtPosition(position);
                nomP.setText(personaSelected.getNombre());
                appP.setText(personaSelected.getApellido());
                correoP.setText(personaSelected.getEmail());
                passwordP.setText(personaSelected.getPassword());
    //            combRoles.getSelectedItem(personaSelected.getRol());


            }
        });
    }

    private void listarDatos() {

    databaseReference.child("Usu").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            listPerson.clear();
            for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                Persona p= objSnaptshot.getValue(Persona.class);
                listPerson.add(p);
                arrayAdapterpersona= new ArrayAdapter<Persona>(Usuarios.this, android.R.layout.simple_list_item_1, listPerson);
                listV_personas.setAdapter(arrayAdapterpersona);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    }

    private void listarDatosRol() {

        databaseReference.child("Rol").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listrol.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Rol r= objSnaptshot.getValue(Rol.class);
                    listrol.add(r);


                    arrayAdapterrol= new ArrayAdapter<Rol>(Usuarios.this, android.R.layout.simple_spinner_item
                            , listrol);
                    combRoles.setAdapter(arrayAdapterrol);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
       // firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    private void control(){
        final String uidIII= UUID.randomUUID().toString();
        final String nomEEE =nomP.getText().toString();
        final String usuaRRRR =appP.getText().toString();
        final String emailIIII =correoP.getText().toString();
        final String passIII =passwordP.getText().toString();
        final String rolII= combRoles.getSelectedItem().toString();

        final Persona per = new Persona(uidIII,nomEEE,usuaRRRR,emailIIII,passIII, rolII);



        if(TextUtils.isEmpty(nomEEE)){
            Toast.makeText(this,"Ingrese el nombre", Toast.LENGTH_SHORT).show();
            nomP.setError("Es necesario ingresar el nombre");
            nomP.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(usuaRRRR)){
            Toast.makeText(this,"Ingrese el apellido", Toast.LENGTH_SHORT).show();
            appP.setError("Es necesario ingresar el apellido");
            appP.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(emailIIII)){
            Toast.makeText(this,"Ingrese el correo", Toast.LENGTH_SHORT).show();
            correoP.setError("Es necesario ingresar el correo");
            correoP.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailIIII).matches()){
            correoP.setError("Ingrese un correo válido");
            correoP.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(passIII)){
            Toast.makeText(this,"Ingrese la contraseña", Toast.LENGTH_SHORT).show();
            passwordP.setError("Es necesario ingresar la contraseña");
            passwordP.requestFocus();
            return;
        }


        firebaseAuth.createUserWithEmailAndPassword(emailIIII,passIII)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Persona persona = new Persona(uidIII,nomEEE,usuaRRRR,emailIIII,passIII,rolII);
                            FirebaseDatabase.getInstance().getReference("Usu")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(persona).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){


                                        Toast.makeText(Usuarios.this, "Registro exitoso",Toast.LENGTH_SHORT).show();

                                    }else {

                                    }

                                }
                            });

                        }else {
                            Toast.makeText(Usuarios.this, "Could not register, please try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
       /* databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(per.getNombre()).exists()){
                    //   mDatabase.child(per.getNombre()).setValue(per);
                    Toast.makeText(Usuarios.this, "Usuario existe", Toast.LENGTH_SHORT).show();

                }else{
                    databaseReference.child("Usu").child(per.getUid()).setValue(per);
                    Toast.makeText(Usuarios.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.icon_add: {

                control();

               /* if (nombre.equals("")|| email.equals("")|| password.equals("")|| app.equals("")|| rol.equals("")) {
                    validacion();

                } else {
                    Persona p = new Persona();
                    p.setUid(uid);
                    p.setNombre(nombre);
                    p.setApellido(app);
                    p.setEmail(email);
                    p.setPassword(password);
                    p.setRol(rol);
                  //  presentadorRegistro.signUpUser(email, password, nombre, app,uid,rol);
                   databaseReference.child("Usu").child(p.getUid()).setValue(p);
                    Toast.makeText(this, "Agregado", Toast.LENGTH_LONG).show();
                    limpriarCajas();*/
                    break;
                }

            case R.id.icon_save:{
                Persona p = new Persona();
                p.setUid(personaSelected.getUid());
                p.setNombre(nomP.getText().toString());
                p.setApellido(appP.getText().toString());
                p.setEmail(correoP.getText().toString());
                p.setPassword(passwordP.getText().toString());
                p.setRol(combRoles.getSelectedItem().toString());
                FirebaseDatabase.getInstance().getReference("Usu")
                        .child(FirebaseAuth.getInstance().getUid())
                        .setValue(p);
                //databaseReference.child("Usu").child(p.getUid()).setValue(p);

                Toast.makeText(this,"Modificado", Toast.LENGTH_LONG).show();
                limpriarCajas();
                break;
            }
//
            case R.id.icon_delete:{
                Persona p = new Persona();
                p.setNombre(personaSelected.getNombre());
                FirebaseDatabase.getInstance().getReference("Usu")
                        .child(FirebaseAuth.getInstance().getUid()).removeValue();
                //databaseReference.child("Usu").child(p.getNombre()).removeValue();

                Toast.makeText(this,"Borrado", Toast.LENGTH_LONG).show();
                limpriarCajas();
                break;
            }

            default:break;
        }
        return true;
    }

    private void limpriarCajas() {
        nomP.setText("");
        correoP.setText("");
        passwordP.setText("");
        appP.setText("");
    }

    private void validacion() {
    String nombre = nomP.getText().toString();
    String correo = correoP.getText().toString();
    String password = passwordP.getText().toString();
    String app = appP.getText().toString();

    if (nombre.equals("")){
        nomP.setError("Requiered");
    }else if (app.equals("")){
            appP.setError("Requiered");
    }else if (correo.equals("")){
        correoP.setError("Requiered");
    }else if (password.equals("")){
        passwordP.setError("Requiered");
    }
        }

    private void validacion2() {
        String nombre = nomP.getText().toString();
        String correo = correoP.getText().toString();
        String password = passwordP.getText().toString();
        String app = appP.getText().toString();

        if (nombre.equals("")){
            nomP.setError("Requiered");
        }else if (app.equals("")){
            appP.setError("Requiered");
        }else if (correo.equals("")){
            correoP.setError("Requiered");
        }else if (password.equals("")){
            passwordP.setError("Requiered");
        }
    }
}
