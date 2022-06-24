package com.aplicacion.pm1ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

public class ActivityPersonaSeleccionada extends AppCompatActivity {
    EditText nombres, apellidos, edad, correo, direccion, codigo;
    Button botonActualizar, botonEliminar, botonRegresar;

    @Override public void onBackPressed() { }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_seleccionada);

        codigo = (EditText) findViewById(R.id.txtCodigo);
        nombres = (EditText) findViewById(R.id.eptxtnombre);
        apellidos = (EditText) findViewById(R.id.eptxtapellidos);
        edad = (EditText) findViewById(R.id.eptxtedad);
        correo = (EditText) findViewById(R.id.eptxtcorreo);
        direccion = (EditText) findViewById(R.id.eptxtdireccion);

        botonActualizar = (Button) findViewById(R.id.btnActualizar);
        botonEliminar = (Button) findViewById(R.id.btnEliminar);
        botonRegresar = (Button) findViewById(R.id.btnRegresar) ;

        codigo.setText(getIntent().getStringExtra("codigo"));
        nombres.setText(getIntent().getStringExtra("nombre"));
        apellidos.setText(getIntent().getStringExtra("apellidos"));
        edad.setText(getIntent().getStringExtra("edad"));
        correo.setText(getIntent().getStringExtra("correo"));
        direccion.setText(getIntent().getStringExtra("direccion"));

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizarPersona();


            }
        });

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarPersona();
            }
        });

        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityListar.class);
                startActivity(intent);
            }
        });

    }

    private void actualizarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String obtenerCodigo = codigo.getText().toString();

        ContentValues valores = new ContentValues();

        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());
        valores.put(Transacciones.direccion, direccion.getText().toString());

        db.update(Transacciones.tablapersonas, valores , Transacciones.id +" = "+obtenerCodigo, null);
        db.close();

        Intent intent = new Intent(getApplicationContext(),ActivityListar.class);
        startActivity(intent);


    }

    private void eliminarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String obtenerCodigo = codigo.getText().toString();

        db.delete(Transacciones.tablapersonas,Transacciones.id +" = "+ obtenerCodigo, null);

        Toast.makeText(getApplicationContext(), "Registro eliminado con exito, Codigo " + obtenerCodigo
                ,Toast.LENGTH_LONG).show();
        db.close();



        //REGRESAR AL MENU PRINCIPAL
        Intent intent = new Intent(getApplicationContext(),ActivityMenu.class);
        startActivity(intent);



    }




}