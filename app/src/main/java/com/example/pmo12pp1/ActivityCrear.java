package com.example.pmo12pp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pmo12pp1.Procesos.SQLliteConexion;
import com.example.pmo12pp1.Procesos.Transacciones;


public class ActivityCrear extends AppCompatActivity {

    Button btnAgregar;
    EditText txtNombre, txtApellido, txtEdad, txtCorreo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        //Casteo de elementos a la interfaz grafica
        init();



        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarEmpleado();
            }
        });
    }

    private void init (){
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtCorreo =(EditText) findViewById(R.id.txtCorreo);
        txtEdad =(EditText) findViewById(R.id.txtEdad);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);

    }

    private void AgregarEmpleado() {
        //Conexion e Insercion a la base de datos
        //nombre base de datos = Transacciones

        SQLliteConexion conexion = new SQLliteConexion(this, Transacciones.NameDataBase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, txtNombre.getText().toString());
        valores.put(Transacciones.apellidos, txtApellido.getText().toString());
        valores.put(Transacciones.correo, txtCorreo.getText().toString());
        valores.put(Transacciones.edad, txtEdad.getText().toString());

        Long resultado = db.insert(Transacciones.tablaEmpleados, Transacciones.id, valores);
        Toast.makeText(this, "Registro Ingresado Correctamente", Toast.LENGTH_LONG).show();
        db.close();// Cerrar conexion a base de datos

        ClearScreen(); //borrar

    }

    //Metodo borrar
    private void ClearScreen() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtEdad.setText("");


    }
}