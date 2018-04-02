package co.quindio.sena.senasoftquindio2016.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.clases.vo.CategoriasVo;
import co.quindio.sena.senasoftquindio2016.clases.Constantes;

/**
 * Created by CHENAO on 31/07/2016.
 */
public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;
    List<CategoriasVo> listaCategorias;
    View vista;
    public CategoriasAdapter(List<CategoriasVo> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutList=R.layout.item_categoria_card_list;
        if (Constantes.visualizacion== Constantes.LIST)
        {
            layoutList=R.layout.item_categoria_card_list;
        }else
        {
            if (Constantes.visualizacion==Constantes.GRID)
            {
                layoutList=R.layout.item_categoria_card_grid;
            }
        }

        vista= LayoutInflater.from(parent.getContext()).inflate(layoutList,parent,false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layParams);

        vista.setOnClickListener(this);

        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolder holderCategorias, int position) {

        if (Constantes.PORTRAIT==true){
            holderCategorias.lugarImg.setImageResource(listaCategorias.get(position).getImagenId());
        }else{
            holderCategorias.lugarImg.setImageResource(listaCategorias.get(position).getIconoId());
        }

        if (Constantes.visualizacion== Constantes.LIST)
        {
            holderCategorias.textNombre.setText(listaCategorias.get(position).getNombre());
            if (vista.findViewById(R.id.tvInfo)!=null)
                holderCategorias.textInfo.setText(listaCategorias.get(position).getInfo());
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
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

        CardView cv;

        ImageView lugarImg;
        TextView textNombre;
        TextView textInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            lugarImg=(ImageView) itemView.findViewById(R.id.imgVersion);
            if (Constantes.visualizacion== Constantes.LIST)
            {
                textNombre=(TextView)itemView.findViewById(R.id.tvNombre);
                if (itemView.findViewById(R.id.tvInfo)!=null)
                     textInfo=(TextView)itemView.findViewById(R.id.tvInfo);
            }
        }
    }
}
