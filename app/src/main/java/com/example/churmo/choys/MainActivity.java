package com.example.churmo.choys;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Cursor cursor;
    ArrayList<Usuarios> listUsu= new ArrayList<>();
    TextView txtCargando;
    int x =  0;
    final DB conexion = new DB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Ws();
        txtCargando= (TextView) findViewById(R.id.txt_cargando);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txtCargando.setVisibility(View.GONE);
                listUsu = extraer();
                VistaRecycler();
                x++;
            }
        },1200);

    }
    public void VistaRecycler(){
        recyclerView = (RecyclerView) findViewById(R.id.ListaUsuarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false));
        AdapterUsuarios adapterUsuarios = new AdapterUsuarios(listUsu);
        adapterUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UsuarioItem.class);
                intent.putExtra("id",listUsu.get(recyclerView.getChildAdapterPosition(v)).getId());
                intent.putExtra("email",listUsu.get(recyclerView.getChildAdapterPosition(v)).getEmail());
                intent.putExtra("first_name",listUsu.get(recyclerView.getChildAdapterPosition(v)).getFirst_name());
                intent.putExtra("last_name",listUsu.get(recyclerView.getChildAdapterPosition(v)).getLast_name());
                intent.putExtra("avatar",listUsu.get(recyclerView.getChildAdapterPosition(v)).getAvatar());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapterUsuarios);
    }

    public void consultas(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://reqres.in/api/users?page=2";
        try{
            JsonObjectRequest request = new JsonObjectRequest(DownloadManager.Request.Method.GET,url,
                    new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    JSONArray jsonArray =response.optJSONArray("datos");
                    SQLiteDatabase db = conexion.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    Log.d("longitud",jsonArray.length()+"");

                    for (int i=0; i< jsonArray.length();i++){
                        Usuarios aux = new Usuarios(jsonArray.optJSONObject(i));
                        values.put("id",aux.getId());
                        values.put("first_name",aux.getFirst_name());
                        values.put("last_name",aux.getLast_name());
                        values.put("email",aux.getEmail());
                        values.put("avatar",aux.getAvatar());

                        if(db.rawQuery("SELECT id FROM usuarios WHERE id LIKE "+ aux.getId(),
                                null).getCount() == 0){
                            db.insert("Usuarios",null,values);
                        }
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),
                            "Conexión a Internet necesaria",
                            Toast.LENGTH_LONG).show();
                }
            });
            queue.add(request);

        }catch (Exception ex){

        }
    }

    public ArrayList<Usuarios> extraer(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] columnas = new String[]{"id", "first_name", "last_name", "email", "avatar"};
        ArrayList<Usuarios> Lista = new ArrayList<>();
        cursor= db.query("usuarios",columnas,null,null,null,
                null,null);
        while(cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String first_name = cursor.getString(cursor.getColumnIndex("first_name"));
            String last_name = cursor.getString(cursor.getColumnIndex("last_name"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String avatar = cursor.getString(cursor.getColumnIndex("avatar"));
            Usuarios data = new Usuarios(id, first_name, last_name, email, avatar);
            Lista.add(data);
        }
        return Lista;
    }
}
