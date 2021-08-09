package com.example.amst6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityRegistro extends AppCompatActivity {

    EditText usuario, clave, confirmarClave, email;
    CheckBox terminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuario = (EditText)findViewById(R.id.etUsuarioR);
        clave = (EditText)findViewById(R.id.etClaveR);
        confirmarClave = (EditText)findViewById(R.id.etConfirmarClave);
        email = (EditText)findViewById(R.id.etEmail);
        terminos = (CheckBox)findViewById(R.id.cbTerminos);
    }

    public void volver(View v){
        Intent i = new Intent(this,ActivityLogin.class);
        startActivity(i);
        finish();
    }

    public void registro(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String usuarioText = usuario.getText().toString();
        String claveText = clave.getText().toString();
        String confirmarClaveText = confirmarClave.getText().toString();
        String emailText = email.getText().toString();

        if(!usuarioText.isEmpty()||!claveText.isEmpty()||!confirmarClaveText.isEmpty()){
            if (terminos.isChecked()) {
                if (claveText.equals(confirmarClaveText)) {
                    bd.execSQL("insert into libreria(usuario,clave,email) values ('" + usuarioText + "','" + claveText + "','"+emailText+"')");
                    bd.close();
                    usuario.setText("");
                    clave.setText("");
                    confirmarClave.setText("");
                    Toast.makeText(this, "Te has registrado, regresa al inicio para iniciar sesion", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Por favor, acepte los terminos y condiciones", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Por favor, llene los campos",Toast.LENGTH_SHORT).show();
        }
    }
}