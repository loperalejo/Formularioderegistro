package com.example.adefault.formularioderegistro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



public class ContactosActivity extends AppCompatActivity {

    private TextView tNombre, tCorreo, tSexo, tFecha, tLugar, tHobbie;
    private ListView lContactos;
    private Button bRegresar;
    String[] nombres = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        tNombre = findViewById(R.id.tNombre);
        tCorreo = findViewById(R.id.tCorreo);
        tSexo = findViewById(R.id.tSexo);
        tFecha = findViewById(R.id.tFecha);
        tLugar = findViewById(R.id.tLugar);
        tHobbie = findViewById(R.id.tHobbies);
        lContactos = findViewById(R.id.lContactos);
        bRegresar = findViewById(R.id.bRegresar);

        Bundle args = getIntent().getExtras();

        if(args != null){
            String sReceived = args.getString("key_string");
            String[][] personas = null;
            Object[] objectArray = (Object[]) args.getSerializable("key_array_array");
            if (objectArray != null) {
                personas = new String[objectArray.length][];
                for (int i = 0; i < objectArray.length; i++) {
                    personas[i] = (String[]) objectArray[i];
                }
            }
            for(int j = 0; j < 20; j++){
                nombres[j] = personas[0][j];
            }
            lContactos.setAdapter(new ArrayAdapter<String>(this, R.layout.mylist, nombres));

            final String[][] finalPersonas = new String[7][20];

            for(int x = 0; x < 7; x++){
                for(int j = 0; j < 20; j++){
                    finalPersonas[x][j] = personas[x][j];
                }
            }

            lContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    int a = 0;
                    tNombre.setText(lContactos.getItemAtPosition(i).toString());
                    tCorreo.setText(finalPersonas[a+2][i]);
                    tSexo.setText(finalPersonas[a+3][i]);
                    tFecha.setText(finalPersonas[a+4][i]);
                    tLugar.setText(finalPersonas[a+5][i]);
                    tHobbie.setText(finalPersonas[a+6][i]);
                }
            });
        }
    }

    public void regresar(View view) {
        finish();
    }
}
