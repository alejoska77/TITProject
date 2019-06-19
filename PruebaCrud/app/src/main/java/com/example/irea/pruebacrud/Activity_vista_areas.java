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
import android.widget.Toast;

import com.example.irea.pruebacrud.model.Area;

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

public class Activity_vista_areas extends AppCompatActivity {

    private List<Area> listniv = new ArrayList<Area>();
    ArrayAdapter<Area> arrayAdapternivel;

    EditText nomN, desN;
    ListView listV_niv;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Area nivelSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_areas);

        FirebaseAuth mAuth= FirebaseAuth.getInstance();
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();

        nomN = findViewById(R.id.etnombreNiv);
        desN = findViewById(R.id.etDescripcionNiv);

        listV_niv = findViewById(R.id.lv_datosNivel);
        inicializarFirebase();
        listarDatosNivel();



        listV_niv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nivelSelected=(Area) parent.getItemAtPosition(position);
                nomN.setText(nivelSelected.getNombreNivel());
                desN.setText(nivelSelected.getDescripcionNivel());

            }
        });
    }

    private void listarDatosNivel() {

        databaseReference.child("Area").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listniv.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Area r= objSnaptshot.getValue(Area.class);
                    listniv.add(r);
                    arrayAdapternivel= new ArrayAdapter<Area>(Activity_vista_areas.this, android.R.layout.simple_list_item_1, listniv);
                    listV_niv.setAdapter(arrayAdapternivel);



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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
private void control(){
    String uidrrr= UUID.randomUUID().toString();

    String nombreNivel =nomN.getText().toString();
    String descripcionNivel =desN.getText().toString();


        if(TextUtils.isEmpty(nombreNivel)){
            Toast.makeText(this,"Ingrese el área", Toast.LENGTH_SHORT).show();
            nomN.setError("Es necesario ingresar el área");
            nomN.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(descripcionNivel)){
            Toast.makeText(this,"Ingrese la descripcion", Toast.LENGTH_SHORT).show();
            desN.setError("Es necesario ingresar la descripción");
            desN.requestFocus();
            return;
        }
    final Area ni = new Area(uidrrr,nombreNivel,descripcionNivel);

    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.child(ni.getNombreNivel()).exists()){
                //   mDatabase.child(per.getNombre()).setValue(per);
                Toast.makeText(Activity_vista_areas.this, "Area existe", Toast.LENGTH_SHORT).show();

            }else{
                databaseReference.child("Area").child(ni.getNombreNivel()).setValue(ni);
                Toast.makeText(Activity_vista_areas.this, "Area Registrado", Toast.LENGTH_SHORT).show();


            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {




        switch (item.getItemId()){
            case R.id.icon_add: {
                control();

/*                if (nombreNivel.equals("")|| descripcionNivel.equals("")) {
                    validacion();

                } else {
                    Area r = new Area();
                    r.setUidNivel(UUID.randomUUID().toString());
                    r.setNombreNivel(nombreNivel);
                    r.setDescripcionNivel(descripcionNivel);
                    databaseReference.child("Area").child(r.getNombreNivel()).setValue(r);
                    Toast.makeText(this, "Agregado", Toast.LENGTH_LONG).show();
                    limpriarCajas();
                    break;
                }*/           break;  }
            case R.id.icon_save:{
                Area r = new Area();
                r.setUidNivel(nivelSelected.getUidNivel());
                r.setNombreNivel(nomN.getText().toString());
                r.setDescripcionNivel(desN.getText().toString());

                databaseReference.child("Area").child(r.getNombreNivel()).setValue(r);

                Toast.makeText(this,"Modificado", Toast.LENGTH_LONG).show();
                limpriarCajas();
                break;
            }
//
            case R.id.icon_delete:{
                Area r = new Area();
                r.setNombreNivel(nivelSelected.getNombreNivel());
                databaseReference.child("Area").child(r.getNombreNivel()).removeValue();

                Toast.makeText(this,"Borrado", Toast.LENGTH_LONG).show();
                limpriarCajas();
                break;
            }

            default:break;
        }
        return true;
    }

    private void limpriarCajas() {
        nomN.setText("");
        desN.setText("");

    }

    private void validacion() {
        String nombreNivel = nomN.getText().toString();
        String descripcionNivel = desN.getText().toString();

        if (nombreNivel.equals("")){
            nomN.setError("Requiere");
        }else if (descripcionNivel.equals("")){
            nomN.setError("Requiere");
        }
    }
}


