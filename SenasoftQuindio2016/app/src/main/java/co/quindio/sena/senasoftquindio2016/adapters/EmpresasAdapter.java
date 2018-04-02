package co.quindio.sena.senasoftquindio2016.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.clases.vo.EmpresaVo;

/**
 * Created by CHENAO on 31/07/2016.
 */
public class EmpresasAdapter extends RecyclerView.Adapter<EmpresasAdapter.ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;
    List<EmpresaVo> listaEmpresas;
    View vista;

    public EmpresasAdapter(List<EmpresaVo> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empresas_card_list,parent,false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layParams);

        vista.setOnClickListener(this);

        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolder holderEmpresa, int position) {
        //holderEmpresa.lugarImg.setImageResource(listaEmpresas.get(position).getImagenId());
        holderEmpresa.lugarImg.setImageBitmap(listaEmpresas.get(position).getLogo());
    //    if (vista.findViewById(R.id.layoutInfo)!=null){
     //       holderEmpresa.textInfo.setText(listaEmpresas.get(position).getDescripcion());
     //       holderEmpresa.textNombre.setText(listaEmpresas.get(position).getNombreEmpresa());
     //   }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return listaEmpresas.size();
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

        CardView cv;

        ImageView lugarImg;
       // TextView textNombre;
     //   TextView textInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardEmpresa);
            lugarImg=(ImageView) itemView.findViewById(R.id.imgVersion);
        //    if (itemView.findViewById(R.id.layoutInfo)!=null){
         //     textNombre=(TextView)itemView.findViewById(R.id.tvNombre);
          //    textInfo=(TextView)itemView.findViewById(R.id.tvInfo);
        //    }
        }
    }
}
