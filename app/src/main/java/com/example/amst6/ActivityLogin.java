package com.example.amst6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity {

    EditText usuario,clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText)findViewById(R.id.etUsuario);
        clave = (EditText)findViewById(R.id.etClave);
    }

    public void login(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        String usuarioText = usuario.getText().toString();
        String claveText = clave.getText().toString();

        if(!usuarioText.isEmpty()){
            Cursor fila = bd.rawQuery("select usuario,clave from libreria where usuario='"+usuarioText+"'",null);
            if(fila.moveToFirst()){
                if (claveText.equals(fila.getString(1))){
                    bd.close();
                    Intent i = new Intent(this,ActivityLibros.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(this,"La contraseña es incorrecta",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"El usuario no se encuentra registrado",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Campos incompletos", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void signUp(View v){
        Intent i = new Intent(this,ActivityRegistro.class);
        startActivity(i);
        finish();
    }
}