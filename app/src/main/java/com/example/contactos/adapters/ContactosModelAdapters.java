package com.example.contactos.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactos.R;
import com.example.contactos.models.ContactoModel;

import java.util.List;

public class ContactosModelAdapters extends RecyclerView.Adapter<ContactosModelAdapters.ContactoVH> {

    private List<ContactoModel> objects;
    private int resource;
    private Context context;

    public ContactosModelAdapters(List<ContactoModel> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactoView = LayoutInflater.from(context).inflate(resource, null);
        contactoView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ContactoVH(contactoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoVH holder, int position) {
        ContactoModel c = objects.get(position);
        holder.lblNombre.setText(c.getNombre());
        holder.lblCiclo.setText(String.valueOf(c.getCiclo()));
        holder.lblTelefono.setText(String.valueOf(c.getTelefono()));

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelete(c, holder.getAdapterPosition()).show();
            }
        });
    }

    private AlertDialog confirmDelete(ContactoModel c, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Estás seguro???");
        builder.setCancelable(false);
        builder.setNegativeButton("Nope", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                objects.remove(c);
                notifyItemRemoved(position);
            }
        });
        return builder.create();
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ContactoVH extends RecyclerView.ViewHolder {
        TextView lblNombre, lblCiclo, lblTelefono;
        ImageButton btnEliminar;
        public ContactoVH(@NonNull View itemView) {
            super(itemView);
            lblNombre = itemView.findViewById(R.id.lblNombreCard);
            lblCiclo = itemView.findViewById(R.id.lblCicloCard);
            lblTelefono = itemView.findViewById(R.id.lblTelefonoCard);
            btnEliminar = itemView.findViewById(R.id.btnEliminarCard);
        }
    }
}
