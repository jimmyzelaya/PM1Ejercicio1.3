package com.aplicacion.pm1ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aplicacion.pm1ejercicio13.Configuraciones.SQLiteConexion;
import com.aplicacion.pm1ejercicio13.Configuraciones.Transacciones;

public class ActivityAgregarPersona extends AppCompatActivity {

    //declaramos las variables
    EditText editTextNombres, editTextApellidos, editTextEdad, editTextCorreo, editTextDireccion;

    Button botonGuardar, botonRegresar;

    @Override public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);

        // inicializamos las variables
        editTextNombres = (EditText) findViewById(R.id.txtnombres);
        editTextApellidos = (EditText) findViewById(R.id.txtApellidos);
        editTextEdad = (EditText) findViewById(R.id.txtedad);
        editTextCorreo = (EditText) findViewById(R.id.txtcorreo);
        editTextDireccion = (EditText) findViewById(R.id.txtDireccion);
        botonGuardar = (Button) findViewById(R.id.tbnGuardar);
        botonRegresar = (Button) findViewById(R.id.btnRegresar1);

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarPersonas();
            }
        });

        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //REGRESAR AL MENU PRINCIPAL
                Intent intent = new Intent(getApplicationContext(),ActivityMenu.class);
                startActivity(intent);
            }
        });

    }

    private void agregarPersonas() {

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);

        SQLiteDatabase db = conexion.getWritableDatabase();


        ContentValues valores = new ContentValues();

        valores.put(Transacciones.nombres, editTextNombres.getText().toString());
        valores.put(Transacciones.apellidos, editTextApellidos.getText().toString());
        valores.put(Transacciones.edad, editTextEdad.getText().toString());
        valores.put(Transacciones.correo, editTextCorreo.getText().toString());
        valores.put(Transacciones.direccion, editTextDireccion.getText().toString());

        Long resultado = db.insert(Transacciones.tablapersonas, Transacciones.id, valores);

        Toast.makeText(getApplicationContext(), "Registro ingreso con exito, Codigo " + resultado.toString()
                ,Toast.LENGTH_LONG).show();

        db.close();

        //despues de guardar se procede a limpiar las cajas de texto
        limpiarPantalla();


        //REGRESAR AL MENU PRINCIPAL
        Intent intent = new Intent(getApplicationContext(),ActivityMenu.class);
        startActivity(intent);


    }

    private void limpiarPantalla() {
        editTextNombres.setText("");
        editTextApellidos.setText("");
        editTextEdad.setText("");
        editTextCorreo.setText("");
        editTextDireccion.setText("");
    }
}