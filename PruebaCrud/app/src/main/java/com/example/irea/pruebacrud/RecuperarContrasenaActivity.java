package com.example.irea.pruebacrud;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarContrasenaActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextCorreo;
    Button buttonEnviar;
    private ImageView imgFlechaAtras;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);
        editTextCorreo= findViewById(R.id.txtEmail);
        buttonEnviar= findViewById(R.id.btnEnviar);
        imgFlechaAtras= findViewById(R.id.imgAtras);
        imgFlechaAtras.setOnClickListener(this);


        firebaseAuth= FirebaseAuth.getInstance();
        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.sendPasswordResetEmail(editTextCorreo.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RecuperarContrasenaActivity.this, "Contraseña enviada al Correo", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity1.class));

                        }else {
                            Toast.makeText(RecuperarContrasenaActivity.this, "Correo invalido no registrado en la aplicación intent de nuevo",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });



    }

    @Override
    public void onClick(View view) {
        if (view == imgFlechaAtras){
            startActivity(new Intent(this,LoginActivity1.class));

        }
    }
}
