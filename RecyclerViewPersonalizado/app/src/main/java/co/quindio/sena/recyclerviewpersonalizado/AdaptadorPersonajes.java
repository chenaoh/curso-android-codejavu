package co.quindio.sena.recyclerviewpersonalizado;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CHENAO on 3/07/2017.
 */

public class AdaptadorPersonajes extends RecyclerView.Adapter<AdaptadorPersonajes.ViewHolderPersonajes>{

    ArrayList<PersonajeVo> listaPersonajes;

    public AdaptadorPersonajes(ArrayList<PersonajeVo> listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }

    @Override
    public ViewHolderPersonajes onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.iem_list_personajes,null,false);
        return new ViewHolderPersonajes(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderPersonajes holder, int position) {
        holder.etiNombre.setText(listaPersonajes.get(position).getNombre());
        holder.etiInformacion.setText(listaPersonajes.get(position).getInfo());
        holder.foto.setImageResource(listaPersonajes.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listaPersonajes.size();
    }

    public class ViewHolderPersonajes extends RecyclerView.ViewHolder {

        TextView etiNombre,etiInformacion;
        ImageView foto;

        public ViewHolderPersonajes(View itemView) {
            super(itemView);
            etiNombre= (TextView) itemView.findViewById(R.id.idNombre);
            etiInformacion= (TextView) itemView.findViewById(R.id.idInfo);
            foto= (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
