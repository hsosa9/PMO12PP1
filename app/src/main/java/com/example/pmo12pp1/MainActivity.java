package com.example.pmo12pp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtnombre, txtapellidos;
    Button btnmostrar, btnsecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnombre = (EditText) findViewById(R.id.txtnombre);
        txtapellidos = (EditText) findViewById(R.id.txtapellidos);
        btnmostrar = (Button) findViewById(R.id.btnmostrar);
        btnsecond = (Button) findViewById(R.id.btnsecond);

        btnmostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Nombre" + txtnombre.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        btnsecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                i.putExtra("nombre", txtnombre.getText().toString());
                i.putExtra("apellido", txtapellidos.getText().toString());
                startActivity(i);



            }
        });
    }
}