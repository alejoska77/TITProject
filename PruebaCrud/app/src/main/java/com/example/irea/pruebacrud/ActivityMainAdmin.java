package com.example.irea.pruebacrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityMainAdmin extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);


        Button mBtnRol = findViewById(R.id.btnRolesad);
        mBtnRol.setOnClickListener(this);
        Button btnniveles = findViewById(R.id.btnNivelesad);
        btnniveles.setOnClickListener(this);
        Button btnUsuarios  = findViewById(R.id.btnUsuariosad);
        btnUsuarios.setOnClickListener(this);
        Button btnresultgeneral= findViewById(R.id.btnresultgeneral);
        btnresultgeneral.setOnClickListener(this);
        Button btnTratamiento= findViewById(R.id.btnTratatmiento);
        btnTratamiento.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

        switch (v.getId())
        {


            case R.id.btnRolesad:
                Intent intent2= new Intent(ActivityMainAdmin.this, VistaRoles.class);
                startActivity(intent2);
                break;
            case R.id.btnNivelesad:
                Intent intent3= new Intent(ActivityMainAdmin.this, Activity_vista_areas.class);
                startActivity(intent3);
                break;
            case R.id.btnUsuariosad:
                Intent intent4= new Intent(ActivityMainAdmin.this, Usuarios.class);
                startActivity(intent4);
                break;
            case R.id.btnresultgeneral:
                Intent intent5= new Intent(ActivityMainAdmin.this, activity_menu_resultados.class);
                startActivity(intent5);
                break;
            case R.id.btnTratatmiento:
                Intent intent6= new Intent(ActivityMainAdmin.this, Activity_Tratamiento.class);
                startActivity(intent6);
        }
    }
}


