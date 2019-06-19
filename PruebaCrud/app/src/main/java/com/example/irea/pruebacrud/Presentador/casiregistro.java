package com.example.irea.pruebacrud.Presentador;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.irea.pruebacrud.Usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

public class casiregistro {

    private Context mContext;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String TAG="PresentadorRegistro";

    public casiregistro(Context mContext, FirebaseAuth mAuth, DatabaseReference mDatabase) {
        this.mContext = mContext;
        this.mAuth = mAuth;
        this.mDatabase = mDatabase;
    }

    public void signUpUsersss(final String uid, final String nombreCompleto, final String username, final String email, final String password, final  String rol){
        final ProgressDialog dialog = new ProgressDialog(mContext);
        dialog.setMessage("Registrando Usuario");
        dialog.setCancelable(false);
        dialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            dialog.dismiss();
                            Map<String, Object> crearUsuario= new HashMap<>();
                            crearUsuario.put("uid", uid);
                            crearUsuario.put("nombre", nombreCompleto);
                            crearUsuario.put("apellido", username);
                            crearUsuario.put("correo", email);

                            crearUsuario.put("password", password);
                            crearUsuario.put("rol", rol);

                            mDatabase.child("Usura").child(task.getResult().getUser().getUid()).updateChildren(crearUsuario);

                            Intent intent= new Intent(mContext, Usuarios.class);
                            mContext.startActivity(intent);

                        } else {
                            dialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(mContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

}


