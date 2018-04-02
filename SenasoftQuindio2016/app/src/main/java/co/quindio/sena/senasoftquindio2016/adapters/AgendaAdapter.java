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
import co.quindio.sena.senasoftquindio2016.clases.vo.AgendaVo;

/**
 * Created by CHENAO on 31/07/2016.
 */
public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;
    List<AgendaVo> listaAgenda;

    public AgendaAdapter(List<AgendaVo> listaAgenda) {
        this.listaAgenda = listaAgenda;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda_card_list,parent,false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layParams);

        vista.setOnClickListener(this);

        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolder holderCategorias, int position) {
        holderCategorias.img.setImageResource(listaAgenda.get(position).getImagenId());
        holderCategorias.textDia.setText(listaAgenda.get(position).getDia());
        holderCategorias.textInfo.setText(listaAgenda.get(position).getInfo());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return listaAgenda.size();
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
        ImageView img;
        TextView textDia;
        TextView textInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardViewDia);
            img=(ImageView) itemView.findViewById(R.id.imgVersion);
            textDia=(TextView)itemView.findViewById(R.id.tvDia);
            textInfo=(TextView)itemView.findViewById(R.id.tvInfo);
        }
    }
}
