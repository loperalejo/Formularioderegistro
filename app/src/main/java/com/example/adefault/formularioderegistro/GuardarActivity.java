package com.example.adefault.formularioderegistro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GuardarActivity extends AppCompatActivity {

    private TextView tNombre, tCorreo, tSexo, tFecha, tLugar, tHobbie;
    private Button bGuardarDatos, bCorregir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar);

        tNombre = findViewById(R.id.tNombre);
        tCorreo = findViewById(R.id.tCorreo);
        tSexo = findViewById(R.id.tSexo);
        tFecha = findViewById(R.id.tFecha);
        tLugar = findViewById(R.id.tLugar);
        tHobbie = findViewById(R.id.tHobbie);
        bCorregir = findViewById(R.id.bCorregir);
        bGuardarDatos = findViewById(R.id.bGuardarDatos);

        Bundle args = getIntent().getExtras();

        if(args!=null) {
            tNombre.setText(args.getString("nombre"));
            tCorreo.setText(args.getString("correo"));
            tSexo.setText(args.getString("sexo"));
            tFecha.setText(args.getString("fecha de nacimiento"));
            tLugar.setText(args.getString("lugar de nacimiento"));
            tHobbie.setText(args.getString("hobbies"));
        }
    }

    public void guardarDatos(View view) {
        Intent intent = new Intent();
        intent.putExtra("Llegaron", "Guardado");
        setResult(RESULT_OK, intent);
        finish();
    }

    public void corregir(View view) {
        finish();
    }
}
