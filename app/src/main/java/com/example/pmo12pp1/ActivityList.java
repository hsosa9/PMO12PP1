package com.example.pmo12pp1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.pmo12pp1.Procesos.Empleados;
import com.example.pmo12pp1.Procesos.SQLliteConexion;
import com.example.pmo12pp1.Procesos.Transacciones;

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
