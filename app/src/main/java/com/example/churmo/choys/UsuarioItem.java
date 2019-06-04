package com.example.churmo.choys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class UsuarioItem extends AppCompatActivity {

    private String id,
            first_name,
            last_name,
            email,
            avatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        cargarElementos();
        setContentView(R.layout.usuario_item);
        TextView txtId = (TextView) findViewById(R.id.txt_id);
        TextView txtNombre = (TextView) findViewById(R.id.txt_nombre);
        TextView txtEmail = (TextView) findViewById(R.id.txt_email);
        ImageView Avatar = (ImageView) findViewById(R.id.imv_avatar);

        txtId.setText("Id: "+ id);
        txtNombre.setText("Nombre "+ first_name +" "+ last_name);
        txtEmail.setText("Email " + email);
        Picasso.with(this)
                .load(avatar)
                .into(Avatar);
    }

    //METODO PARA CARGAR LOS DATOS DE LOS DEMAS USUARIOS
    public void cargarElementos(){

        id=getIntent().getStringExtra("id");
        first_name=getIntent().getStringExtra("first_name");
        last_name=getIntent().getStringExtra("last_name");
        email=getIntent().getStringExtra("email");
        avatar=getIntent().getStringExtra("avatar");
    }

}
