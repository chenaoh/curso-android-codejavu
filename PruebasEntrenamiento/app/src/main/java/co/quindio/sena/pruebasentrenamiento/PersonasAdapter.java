package co.quindio.sena.pruebasentrenamiento;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CHENAO on 31/07/2016.
 */
public class PersonasAdapter extends RecyclerView.Adapter<PersonasAdapter.ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;
    List<PersonaVo> listaPersonas;
    View vista;
    public PersonasAdapter(List<PersonaVo> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_arreglo,parent,false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layParams);

        vista.setOnClickListener(this);

        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolder holderCategorias, int position) {
        holderCategorias.textId.setText(listaPersonas.get(position).getId().toString());
        holderCategorias.textNombre.setText(listaPersonas.get(position).getNombre());
        holderCategorias.textEdad.setText(listaPersonas.get(position).getEdad().toString());
        holderCategorias.textTelefono.setText(listaPersonas.get(position).getTelefono());
        holderCategorias.textDireccion.setText(listaPersonas.get(position).getDireccion());
        holderCategorias.textProfesion.setText(listaPersonas.get(position).getProfesion());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

    public static class ViewHolder  extends RecyclerView.ViewHolder{
        TextView textId;
        TextView textNombre;
        TextView textEdad;
        TextView textTelefono;
        TextView textDireccion;
        TextView textProfesion;

        public ViewHolder(View itemView) {
            super(itemView);
            textId=(TextView)itemView.findViewById(R.id.tvId);
            textNombre=(TextView)itemView.findViewById(R.id.tvNombre);
            textEdad=(TextView)itemView.findViewById(R.id.tvEdad);
            textTelefono=(TextView)itemView.findViewById(R.id.tvTelefono);
            textDireccion=(TextView)itemView.findViewById(R.id.tvDireccion);
            textProfesion=(TextView)itemView.findViewById(R.id.tvProfesion);
        }
    }
}
