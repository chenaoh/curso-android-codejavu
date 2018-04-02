package co.quindio.sena.senasoftquindio2016.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.clases.vo.TematicaCategoriaVo;

/**
 * Created by CHENAO on 31/07/2016.
 */
public class TematicaCategoriasAdapter extends RecyclerView.Adapter<TematicaCategoriasAdapter.ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;
    List<TematicaCategoriaVo> listaTematicas;

    public TematicaCategoriasAdapter(List<TematicaCategoriaVo> listaTematicas) {
        this.listaTematicas = listaTematicas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tematica_list,parent,false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layParams);

        vista.setOnClickListener(this);

        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolder holderTematicas, int position) {
        holderTematicas.textJornada.setText(listaTematicas.get(position).getJornada() );
        holderTematicas.textProceso.setText(listaTematicas.get(position).getProceso());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return listaTematicas.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textJornada;
        TextView textProceso;

        public ViewHolder(View itemView) {
            super(itemView);
            textJornada=(TextView) itemView.findViewById(R.id.tvJornada);
            textProceso=(TextView)itemView.findViewById(R.id.tvProceso);
        }
    }
}
