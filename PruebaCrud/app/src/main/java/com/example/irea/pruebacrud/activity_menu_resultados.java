package com.example.irea.pruebacrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_menu_resultados extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_resultados);



        Button btnresulttest= findViewById(R.id.btnresulttest);
        btnresulttest.setOnClickListener(this);
        Button btnEmocion= findViewById(R.id.btnreconoceemocion);
        btnEmocion.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

        switch (v.getId())
        {


            case R.id.btnresulttest:
                Intent intent5= new Intent(activity_menu_resultados.this, activity_lista_general.class);
                startActivity(intent5);
                break;
            case R.id.btnreconoceemocion:
                Intent intent6= new Intent(activity_menu_resultados.this, activity_lista_detallada_emociones.class);
                startActivity(intent6);
        }
    }
}


