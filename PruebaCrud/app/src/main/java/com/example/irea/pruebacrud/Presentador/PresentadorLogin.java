package com.example.irea.pruebacrud.Presentador;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;


import com.example.irea.pruebacrud.Usuarios;
import com.example.irea.pruebacrud.PrimerActivity;
import com.example.irea.pruebacrud.VistaRoles;
import com.example.irea.pruebacrud.model.Persona;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;


public class PresentadorLogin {

    private Context mContext;
 private String TAG="PresentadorLogin";
    private FirebaseAuth mAuth;
    private DatabaseReference mdatabase;

    public PresentadorLogin(Context mContext, FirebaseAuth mAuth, DatabaseReference mdatabase) {
        this.mContext = mContext;
        this.mAuth = mAuth;
        this.mdatabase = mdatabase;
    }

    public void signInUser(final String email, final String password, final String rol) {

      final ProgressDialog dialog = new ProgressDialog(mContext);
        dialog.setMessage("Ingresando..");
        dialog.setCancelable(false);
        dialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            dialog.dismiss();
                            mdatabase.child("Usuarios").child(task.getResult().getUser().getUid()).child("Titulo").setValue("Logins");
                            if(mdatabase.child("Usuarios").child(rol).equals(rol))

                            {
//                            Intent intent = new Intent(mContext, Ruleta.class);
  //                          mContext.startActivity(intent);

                        }else if(mdatabase.child("Usuarios").child(rol).equals("User")) {
                                Intent intent = new Intent(mContext, VistaRoles.class);
                                mContext.startActivity(intent);
                            }
                            }else
                             {
                            dialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(mContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }




                    public void onDataChange( DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(email).exists()){
                            if(!email.isEmpty()){
                                Persona login = dataSnapshot.child(email).getValue(Persona.class);
                                if(login.getPassword().equals(password)){
                                    Log.d(TAG, "signInWithEmail:success");
                                    if (login.getRol().equals("User")){
                                        Intent intent = new Intent(mContext, PrimerActivity.class);
                                        mContext.startActivity(intent);
                                    }else{
                                        Intent intent2 = new Intent(mContext, Usuarios.class);
                                        mContext.startActivity(intent2);
                                    }
                                }else{
                                    Log.d(TAG, "signInWithEmail:success");
                                }
                            }
                            else {
                                Log.d(TAG, "signInWithEmail:success");
                            }
                        }
                    }




                });
    }
}