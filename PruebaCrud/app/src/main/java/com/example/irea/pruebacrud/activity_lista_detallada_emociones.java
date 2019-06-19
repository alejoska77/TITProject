package com.example.irea.pruebacrud;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.irea.pruebacrud.model.Emociones;
import com.example.irea.pruebacrud.model.Persona;
import com.example.irea.pruebacrud.model.Preguntas;
import com.example.irea.pruebacrud.model.Resultados;
import com.example.irea.pruebacrud.model.Resultados_Emociones;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_lista_detallada_emociones extends AppCompatActivity {

    private List<Emociones> listresultper = new ArrayList<Emociones>();
    ArrayAdapter<Emociones> arrayAdapteresultoer;

    private List<Persona> listresult = new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapteresult;

    private List<Resultados_Emociones> listresultados = new ArrayList<Resultados_Emociones>();
    ArrayAdapter<Resultados_Emociones> arrayAdapteresultados;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ListView listV_resul;
    ListView listV_resul2;

    Resultados_Emociones rolSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_detallada_emociones);

        listV_resul = findViewById(R.id.lv_datosResult);
        listV_resul2 = findViewById(R.id.lv_datosResult2);

        inicializarFirebase();
        listarDatosResult();

        listV_resul.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rolSelected=(Resultados_Emociones) parent.getItemAtPosition(position);
                databaseReference.child("Emociones").child(rolSelected.getId()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        listresultper.clear();
                        for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                            Emociones s= objSnaptshot.getValue(Emociones.class);
                            listresultper.add(s);
                            arrayAdapteresultoer =new ArrayAdapter<Emociones>(activity_lista_detallada_emociones.this, android.R.layout.simple_list_item_1, listresultper);
                            listV_resul2.setAdapter(arrayAdapteresultoer);




                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void listarDatosdestalle() {

        databaseReference.child("Emociones").child(rolSelected.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listresultper.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Emociones s= objSnaptshot.getValue(Emociones.class);
                    listresultper.add(s);
                    arrayAdapteresultoer =new ArrayAdapter<Emociones>(activity_lista_detallada_emociones.this, android.R.layout.simple_list_item_2, listresultper);
                    listV_resul2.setAdapter(arrayAdapteresultoer);




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void listarDatosResult() {

        databaseReference.child("ResultadosEmociones").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listresultados.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Resultados_Emociones r= objSnaptshot.getValue(Resultados_Emociones.class);
                    listresultados.add(r);
                    arrayAdapteresultados= new ArrayAdapter<Resultados_Emociones>(activity_lista_detallada_emociones.this, android.R.layout.simple_list_item_1, listresultados);
                    listV_resul.setAdapter(arrayAdapteresultados);

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

}
