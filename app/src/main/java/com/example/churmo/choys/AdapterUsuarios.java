package com.example.churmo.choys;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterUsuarios extends RecyclerView.Adapter<AdapterUsuarios.ViewHolderDatos>
        implements View.OnClickListener{

    private View view;
    private View.OnClickListener listener;
    ArrayList<Usuarios> ListaUsuarios;

    public AdapterUsuarios(ArrayList<Usuarios> listaUsuarios) {
        ListaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public AdapterUsuarios.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.usuario_lista,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUsuarios.ViewHolderDatos holder, int i) {
        holder.txtId.setText("Id: "+ ListaUsuarios.get(i).getId());
        holder.txtNombre.setText("Nombre " + ListaUsuarios.get(i).getFirst_name() + " "
                + ListaUsuarios.get(i).getLast_name());
        holder.txtEmail.setText("Email " + ListaUsuarios.get(i).getEmail());
        Picasso.with(view.getContext())
                .load(ListaUsuarios.get(i).getAvatar())
                .into(holder.Avatar);
    }

    @Override
    public int getItemCount() {
        return ListaUsuarios.size();
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
        if(listener!= null){
            listener.onClick(v);
        }
    }

    //CREAR LAS VARIABLES QUE SE USARAN CON EL LAYOUT DE USUARIO_LISTA
    public class DatosViewHolder extends RecyclerView.ViewHolder {
        TextView txtId;
        TextView txtNombre;
        TextView txtEmail;
        ImageView Avatar;
        public DatosViewHolder(@NonNull View itemView) {
            super(itemView);
            llenarLista();
        }

        //LLENAR LA LISTA DE USUARIOS CON LOS DATOS DE CADA CAMPO
        public void llenarLista() {
            txtId = (TextView) itemView.findViewById(R.id.txt_id);
            txtNombre = (TextView) itemView.findViewById(R.id.txt_nombre);
            txtEmail = (TextView) itemView.findViewById(R.id.txt_email);
            Avatar = (ImageView) itemView.findViewById(R.id.imv_avatar);
        }
    }

}