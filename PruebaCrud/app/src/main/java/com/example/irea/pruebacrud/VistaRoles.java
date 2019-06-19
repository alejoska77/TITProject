package com.example.irea.pruebacrud;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.irea.pruebacrud.model.Persona;
import com.example.irea.pruebacrud.model.Rol;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VistaRoles extends AppCompatActivity {

    private List<Rol> listrol = new ArrayList<Rol>();
    ArrayAdapter<Rol> arrayAdapterrol;

    EditText nomR, desR;
    ListView listV_rol;
    Spinner comboRoles;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Rol rolSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_roles);


        nomR = findViewById(R.id.etnombreRol);
        desR = findViewById(R.id.etDescripcionRol);
        comboRoles=(Spinner) findViewById(R.id.comboRoles);
        listV_rol = findViewById(R.id.lv_datosRoles);
        inicializarFirebase();
        listarDatosRol();



        listV_rol.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rolSelected=(Rol) parent.getItemAtPosition(position);
                nomR.setText(rolSelected.getNombreRol());
                desR.setText(rolSelected.getDescripcionRol());

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
                    arrayAdapterrol= new ArrayAdapter<Rol>(VistaRoles.this, android.R.layout.simple_list_item_1, listrol);
                    listV_rol.setAdapter(arrayAdapterrol);

                    arrayAdapterrol= new ArrayAdapter<Rol>(VistaRoles.this, android.R.layout.simple_spinner_item
                            , listrol);
                    comboRoles.setAdapter(arrayAdapterrol);


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
        String uidsss= UUID.randomUUID().toString();
        String nombreRol =nomR.getText().toString();
        String descripcionRol =desR.getText().toString();



        if(TextUtils.isEmpty(nombreRol)){
            Toast.makeText(this,"Ingrese el Rol", Toast.LENGTH_SHORT).show();
            nomR.setError("Es necesario ingresar el nombre del rol");
            nomR.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(descripcionRol)){
            Toast.makeText(this,"Ingrese la Descripción", Toast.LENGTH_SHORT).show();
            desR.setError("Es necesario ingresar la descripción");
            desR.requestFocus();
            return;
        }
        final Rol ro = new Rol(uidsss,nombreRol,descripcionRol);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(ro.getNombreRol()).exists()){
                    //   mDatabase.child(per.getNombre()).setValue(per);
                    Toast.makeText(VistaRoles.this, "Rol existe", Toast.LENGTH_SHORT).show();

                }else{
                    databaseReference.child("Rol").child(ro.getNombreRol()).setValue(ro);
                    Toast.makeText(VistaRoles.this, "Rol Registrado", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
          /*      if (nombreRol.equals("")|| descripcionRol.equals("")) {
                    validacion();

                } else {
                    Rol r = new Rol();
                    r.setUidRol(UUID.randomUUID().toString());
                    r.setNombreRol(nombreRol);
                    r.setDescripcionRol(descripcionRol);
                    databaseReference.child("Rol").child(r.getUidRol()).setValue(r);
                    Toast.makeText(this, "Agregado", Toast.LENGTH_LONG).show();
                    limpriarCajas();
                    break;
                }*/
                break;
            }
            case R.id.icon_save:{
                Rol r = new Rol();
                r.setUidRol(rolSelected.getUidRol());
                r.setNombreRol(nomR.getText().toString());
                r.setDescripcionRol(desR.getText().toString());
                databaseReference.child("Rol").child(r.getNombreRol()).setValue(r);

                Toast.makeText(this,"Modificado", Toast.LENGTH_LONG).show();
                limpriarCajas();
                break;
            }
//
            case R.id.icon_delete:{
                Rol r = new Rol();
                r.setNombreRol(rolSelected.getNombreRol());
                databaseReference.child("Rol").child(r.getNombreRol()).removeValue();

                Toast.makeText(this,"Borrado", Toast.LENGTH_LONG).show();
                limpriarCajas();
                break;
            }

            default:break;
        }
        return true;
    }

    private void limpriarCajas() {
        nomR.setText("");
        desR.setText("");

    }

    private void validacion() {
        String nombreRol = nomR.getText().toString();
        String descripcionRol = desR.getText().toString();

        if (nombreRol.equals("")){
            nomR.setError("Requiered");
        }else if (descripcionRol.equals("")){
            nomR.setError("Requiered");
        }
    }
}


