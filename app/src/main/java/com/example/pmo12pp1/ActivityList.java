package com.example.pmo12pp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pmo12pp1.Procesos.Empleados;
import com.example.pmo12pp1.Procesos.SQLliteConexion;
import com.example.pmo12pp1.Procesos.Transacciones;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ActivityList extends AppCompatActivity {

    SQLliteConexion conexion;
    ListView listemple;
    ArrayList<Empleados>listaempleados;
    ArrayList<String>ArregloEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listemple = (ListView) findViewById(R.id.listviewEmple);
        conexion = new SQLliteConexion(this, Transacciones.NameDataBase, null, 1);
        ObtenerListaEmpleados();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ArregloEmpleados);
        listemple.setAdapter(adp);

        listemple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String Info = "ID : " + listaempleados.get(position).getId() + "\n" +
                        listaempleados.get(position).getNombres();

                Snackbar.make(view, Info,Snackbar.LENGTH_LONG).show();

                Intent intetShare = new Intent();
                intetShare.setAction(Intent.ACTION_SEND);
                intetShare.putExtra(Intent.EXTRA_TEXT, Info);
                intetShare.setType("text/plain");
                Intent Share = Intent.createChooser(intetShare, null);
                startActivity(Share);

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
