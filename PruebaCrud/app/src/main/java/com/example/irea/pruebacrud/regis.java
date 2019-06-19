package com.example.irea.pruebacrud;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.irea.pruebacrud.model.Persona;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class regis extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister; //Definción de variable botón para registrar
    private EditText editTextEmail; //Definición de variable de texto para ingresar el email
    private EditText editTextPassword; //Definción de variable de texto para el ingreso de contraseña
    private TextView textViewLogin; //Definición de variable de texto para acceso al login
    private EditText editTextNombre; // Definición de variable de texto para ingreso de nombre
    private EditText editTextApellido; // Definición de variable de texto para ingreso de nombre
    private TextView textViewRol;
    private ImageView imgFlechaAtras;

    private ProgressDialog progressDialog; //Definición de variable de Dialogo
    private FirebaseAuth firebaseAuth; //Definición de variable para registro de usuarios en firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_regis);



        progressDialog= new ProgressDialog(this);
        firebaseAuth= FirebaseAuth.getInstance();
/*        if (firebaseAuth.getCurrentUser() !=null){
            finish();
            startActivity(new Intent(getApplicationContext(), activity_main_use.class));

        }
*/
        buttonRegister =(Button) findViewById(R.id.btnRegistrar);
        editTextEmail =(EditText) findViewById(R.id.txtEmail);
        editTextPassword =(EditText) findViewById(R.id.txtPassword);
        textViewLogin =(TextView) findViewById(R.id.txtvLogin);
        textViewRol =(TextView) findViewById(R.id.txtRol);
        editTextNombre= (EditText) findViewById(R.id.txtNombre);
        imgFlechaAtras= findViewById(R.id.imgAtras);
        editTextApellido=findViewById(R.id.txtApellido);
        buttonRegister.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);
        imgFlechaAtras.setOnClickListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser()!=null){

        }
    }

    private void registerUser(){
        final   String uidIII= UUID.randomUUID().toString();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String name = editTextNombre.getText().toString().trim();
        final String rol =textViewRol.getText().toString().trim();
        final String apellido=editTextApellido.getText().toString().trim();
        //final String edad = editTextEdad.getText().toString().trim(); //Definción del tipo de variable edad

        if (name.isEmpty()){
            Toast.makeText(this,"Ingrese el nombre", Toast.LENGTH_SHORT).show();
            editTextNombre.setError("Es necesario ingresar el nombre");
            editTextNombre.requestFocus();
            return;
        }

        if (apellido.isEmpty()){
            Toast.makeText(this,"Ingrese el apellido", Toast.LENGTH_SHORT).show();
            editTextApellido.setError("Es necesario ingresar el apellido");
            editTextApellido.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Ingrese un correo electronico", Toast.LENGTH_SHORT).show();
            editTextEmail.setError("Es necesario ingresar el correo");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Ingrese un correo válido");
            editTextEmail.requestFocus();
            return;
        }


        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Ingrese la contraseña", Toast.LENGTH_SHORT).show();
            editTextPassword.setError("Es necesario ingresar la contraseña");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            Toast.makeText(this,"Ingrese su contraseña", Toast.LENGTH_SHORT).show();
            editTextPassword.setError("Contraseña debe tener por lo menos 6 caracteres");
            editTextPassword.requestFocus();
            return;
        }
        /*if (edad.isEmpty()){
            Toast.makeText(this,"Ingrese la edad", Toast.LENGTH_SHORT).show();
            editTextEdad.setError("Es necesario ingresar la edad");
            editTextEdad.requestFocus();
            return;
        }*/

        progressDialog.setMessage("Registrando Usuario.....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Persona persona = new Persona(uidIII,name,apellido,email,password,rol);
                            FirebaseDatabase.getInstance().getReference("Usu")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(persona).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){

                                        startActivity(new Intent(getApplicationContext(),LoginActivity1.class));


                                        Toast.makeText(regis.this, "Registro exitoso",Toast.LENGTH_SHORT).show();

                                    }else {

                                    }

                                }
                            });

                        }else {
                            Toast.makeText(regis.this, "No se pudo registrar, intente de nuevo",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void onClick(View view) {
        if (view == buttonRegister){
            registerUser();
        }
        if (view == textViewLogin){
            startActivity(new Intent(this,LoginActivity1.class));

        }
        if (view == imgFlechaAtras){
            startActivity(new Intent(this,LoginActivity1.class));

        }

    }
}
