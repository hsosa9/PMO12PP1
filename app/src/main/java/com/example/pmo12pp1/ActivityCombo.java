package com.example.pmo12pp1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pmo12pp1.Procesos.Empleados;
import com.example.pmo12pp1.Procesos.SQLliteConexion;
import com.example.pmo12pp1.Procesos.Transacciones;

import java.util.ArrayList;

public class ActivityCombo extends AppCompatActivity {

    SQLliteConexion conexion;
    Spinner comboEmpleado;
    EditText nombres, apellidos, edad, correo;
    ArrayList<Empleados>listaempleados;
    ArrayList<String>ArregloEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        nombres = (EditText) findViewById(R.id.txtcomnombres);
        apellidos= (EditText) findViewById(R.id.txtcomapellidos);
        edad= (EditText) findViewById(R.id.txtcomedad);
        correo = (EditText) findViewById(R.id.txtaconemail);
        comboEmpleado = (Spinner) findViewById(R.id.comboPersona);

        conexion = new SQLliteConexion(this, Transacciones.NameDataBase, null, 1);

        ObtenerListaEmpleados();
        //Llenar combo box
        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ArregloEmpleados);
        comboEmpleado.setAdapter(adp);

        comboEmpleado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                nombres.setText(listaempleados.get(position).getNombres());
                apellidos.setText(listaempleados.get(position).getApellidos());
                edad.setText(listaempleados.get(position).getEdad().toString());
                correo.setText(listaempleados.get(position).getCorreo());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void ObtenerListaEmpleados() {

        SQLiteDatabase db = conexion.getReadableDatabase();
        Empleados empleado = null;
        listaempleados = new ArrayList<Empleados>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablaEmpleados, null);

        while (cursor.moveToNext()){
            empleado = new Empleados();
            empleado.setId(cursor.getInt(0));
            empleado.setNombres(cursor.getString(1));
            empleado.setApellidos(cursor.getString(2));
            empleado.setEdad(cursor.getInt(3));
            empleado.setCorreo(cursor.getString(4));
            listaempleados.add(empleado);
        }
        cursor.close();
        fllList();
    }

    private void fllList() {
        ArregloEmpleados = new ArrayList<String>();
        for (int i = 0; i<listaempleados.size(); i++ ){
            ArregloEmpleados.add(listaempleados.get(i).getId() + " + " +
                    listaempleados.get(i).getNombres() + " + " +
                    listaempleados.get(i).getApellidos() + " + ");
        }
    }

}