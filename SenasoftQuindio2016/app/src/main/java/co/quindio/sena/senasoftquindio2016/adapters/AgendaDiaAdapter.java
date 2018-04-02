package co.quindio.sena.senasoftquindio2016.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.clases.vo.AgendaDiaVo;

/**
 * Created by CHENAO on 31/07/2016.
 */
public class AgendaDiaAdapter extends RecyclerView.Adapter<AgendaDiaAdapter.ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;
    List<AgendaDiaVo> listaAgendaDia;

    public AgendaDiaAdapter(List<AgendaDiaVo> listaAgendaDia) {
        this.listaAgendaDia = listaAgendaDia;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda_dia_list,parent,false);
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_agenda,parent,false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layParams);

        vista.setOnClickListener(this);

        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolder holderCategorias, int position) {
       holderCategorias.textHora.setText(listaAgendaDia.get(position).getHora());
       holderCategorias.textTema.setText(listaAgendaDia.get(position).getTema());
       holderCategorias.textConferencista.setText(listaAgendaDia.get(position).getConferencista());
       holderCategorias.textLugar.setText(listaAgendaDia.get(position).getLugar());

       if (listaAgendaDia.get(position).getConferencista().equals("NA")){
           holderCategorias.tituloConferencista.setText("");
           holderCategorias.textConferencista.setText("");
       }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return listaAgendaDia.size();
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
        TextView textHora;
        TextView textTema;
        TextView textConferencista;
        TextView tituloConferencista;
        TextView textLugar;

        public ViewHolder(View itemView) {
            super(itemView);
            textHora=(TextView) itemView.findViewById(R.id.tvHora);
            textTema=(TextView)itemView.findViewById(R.id.tvTema);
            textConferencista=(TextView)itemView.findViewById(R.id.tvConferencista);
            textLugar=(TextView)itemView.findViewById(R.id.tvLugar);
            tituloConferencista= (TextView) itemView.findViewById(R.id.textConferencista);
        }
    }
}
