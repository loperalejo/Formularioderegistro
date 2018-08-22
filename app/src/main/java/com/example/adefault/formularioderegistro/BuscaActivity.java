package com.example.adefault.formularioderegistro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuscaActivity extends AppCompatActivity {

    private EditText eNombre;
    private TextView tNombre, tCorreo, tSexo, tFecha, tLugar, tHobbie;
    private Button bBuscar, bRegresar, bEliminar;
    final String[][] finalPersonas = new String[7][20];
    int delete = 0;
    int el = 0;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);

        eNombre = findViewById(R.id.eNombre);
        tNombre = findViewById(R.id.tNombre);
        tCorreo = findViewById(R.id.tCorreo);
        tSexo = findViewById(R.id.tSexo);
        tFecha = findViewById(R.id.tFecha);
        tLugar = findViewById(R.id.tLugar);
        tHobbie = findViewById(R.id.tHobbies);
        bBuscar = findViewById(R.id.bBuscar);
        bRegresar = findViewById(R.id.bRegresar);
        bEliminar = findViewById(R.id.bEliminar);

        Bundle args = getIntent().getExtras();

        if (args != null) {
            String sReceived = args.getString("key_string");
            String[][] personas = null;
            Object[] objectArray = (Object[]) args.getSerializable("key_array_array");
            if (objectArray != null) {
                personas = new String[objectArray.length][];
                for (int i = 0; i < objectArray.length; i++) {
                    personas[i] = (String[]) objectArray[i];
                }
            }

            for (int x = 0; x < 7; x++) {
                for (int j = 0; j < 20; j++) {
                    finalPersonas[x][j] = personas[x][j];
                }
            }
        }
    }

    public void busca(View view) {
        if(eNombre.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingresa el nombre completo del contacto", Toast.LENGTH_SHORT).show();
        } else {
            int ok = 1;
            int i = 0;
            nombre = eNombre.getText().toString();
            while(ok == 1){
                if(i > 19){
                    Toast.makeText(this, "El contacto ingresado no existe", Toast.LENGTH_SHORT).show();
                    ok = 0;
                }else if(finalPersonas[0][i].equals(nombre)){
                    tNombre.setText(finalPersonas[0][i]);
                    tCorreo.setText(finalPersonas[2][i]);
                    tSexo.setText(finalPersonas[3][i]);
                    tFecha.setText(finalPersonas[4][i]);
                    tLugar.setText(finalPersonas[5][i]);
                    tHobbie.setText(finalPersonas[6][i]);
                    ok = 0;
                    el = 1;
                }
                i++;
            }
        }
    }

    public void atras(View view) {
        Intent intent = new Intent();
        String stringToSend = "Hello";
        intent.putExtra("key_string", stringToSend);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("key_array_array",  finalPersonas);
        intent.putExtras(mBundle);
        if(delete == 1){
            intent.putExtra("contacto", "eliminado");
        }
        setResult(RESULT_OK, intent);
        finish();
    }

    public void eliminar(View view) {
        if(el == 0){
            Toast.makeText(this, "Busca el contacto que deseas eliminar", Toast.LENGTH_SHORT).show();
        } else {
            String eliminar = tNombre.getText().toString();
            delete = 1;
            el = 0;
            int ok = 1;
            int cont = 0;
            int i = 0;

            while(ok == 1){
                if(cont < 20){
                    if(finalPersonas[0][cont].equals("")){
                        ok = 0;
                    } else {
                        cont++;
                    }
                } else {
                    ok = 0;
                }
            }
            ok = 1;
            while(ok == 1) {
                if(finalPersonas[0][i].equals(eliminar)){
                    finalPersonas[0][i] = "";
                    finalPersonas[1][i] = "";
                    finalPersonas[2][i] = "";
                    finalPersonas[3][i] = "";
                    finalPersonas[4][i] = "";
                    finalPersonas[5][i] = "";
                    finalPersonas[6][i] = "";
                    ok = 0;
                } else {
                    i++;
                }
            }
            tNombre.setText("");
            tCorreo.setText("");
            tSexo.setText("");
            tFecha.setText("");
            tLugar.setText("");
            tHobbie.setText("");
            Toast.makeText(this, "Contacto eliminado", Toast.LENGTH_SHORT).show();
            if((i+1) < cont){
                while((i+1) < cont){
                    for(int x = 0; x < 7; x++){
                        finalPersonas[x][i] = finalPersonas[x][i+1];
                    }
                    i++;
                }
                for(int x = 0; x < 7; x++){
                    finalPersonas[x][i] = "";
                }
            }
        }
    }
}
