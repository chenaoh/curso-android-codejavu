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
import co.quindio.sena.senasoftquindio2016.clases.vo.ProyectoCreamasVo;

/**
 * Created by CHENAO on 31/07/2016.
 */
public class CreamasProjectAdapter extends RecyclerView.Adapter<CreamasProjectAdapter.ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;
    List<ProyectoCreamasVo> listaProjectos;

    public CreamasProjectAdapter(List<ProyectoCreamasVo> listaProjectos) {
        this.listaProjectos = listaProjectos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_creamas_card_list,parent,false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layParams);

        vista.setOnClickListener(this);

        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolder holderProyecto, int position) {
      //  holderProyecto.textInfo.setText(listaProjectos.get(position).getDescripcion());
        holderProyecto.lugarImg.setImageBitmap(listaProjectos.get(position).getLogo());
       // holderCategorias.textNombre.setText(listaProjectos.get(position).getNombre());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return listaProjectos.size();
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
            cv = (CardView)itemView.findViewById(R.id.cardProject);
            lugarImg=(ImageView) itemView.findViewById(R.id.imgProject);
         //   textNombre=(TextView)itemView.findViewById(R.id.tvNombre);
         //   textInfo=(TextView)itemView.findViewById(R.id.tvInfoProject);
        }
    }
}
