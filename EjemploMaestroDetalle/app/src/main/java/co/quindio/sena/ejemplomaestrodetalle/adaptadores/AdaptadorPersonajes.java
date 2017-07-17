package co.quindio.sena.ejemplomaestrodetalle.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import co.quindio.sena.ejemplomaestrodetalle.R;
import co.quindio.sena.ejemplomaestrodetalle.entidades.PersonajeVo;
import co.quindio.sena.ejemplomaestrodetalle.utilidades.Utilidades;

/**
 * Created by CHENAO on 13/07/2017.
 */

public class AdaptadorPersonajes extends RecyclerView.Adapter<AdaptadorPersonajes.PersonajesViewHolder> implements View.OnClickListener {

    ArrayList<PersonajeVo> listaPersonajes;
    private View.OnClickListener listener;

    public AdaptadorPersonajes(ArrayList<PersonajeVo> listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }

    @Override
    public PersonajesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layParams);

        view.setOnClickListener(this);

        return new PersonajesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonajesViewHolder holder, int position) {
        holder.txtNombre.setText(listaPersonajes.get(position).getNombre());
        if (Utilidades.PORTRAIT==true){
            holder.txtInformacion.setText(listaPersonajes.get(position).getInfo());
        }

        holder.foto.setImageResource(listaPersonajes.get(position).getImagenId());
    }

    @Override
    public int getItemCount() {
        return listaPersonajes.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class PersonajesViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre,txtInformacion;
        ImageView foto;

        public PersonajesViewHolder(View itemView) {
            super(itemView);
            txtNombre= (TextView) itemView.findViewById(R.id.idNombre);
            if (Utilidades.PORTRAIT==true){
                txtInformacion= (TextView) itemView.findViewById(R.id.idInfo);
            }

            foto= (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
