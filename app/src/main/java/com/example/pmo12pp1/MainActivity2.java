package com.example.pmo12pp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    EditText txtresultado;
    EditText txtresultado2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtresultado = (EditText) findViewById(R.id.txtresultado);
        txtresultado2 = (EditText) findViewById(R.id.txtresultado2);

        txtresultado.setText(getIntent().getExtras().getString("nombre"));
        txtresultado2.setText(getIntent().getExtras().getString("apellido"));

}
}