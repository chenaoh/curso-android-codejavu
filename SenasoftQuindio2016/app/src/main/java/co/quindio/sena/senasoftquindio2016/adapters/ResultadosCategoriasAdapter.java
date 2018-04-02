package co.quindio.sena.senasoftquindio2016.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.clases.vo.ResultadosCategoriaVo;

/**
 * Created by CHENAO on 31/07/2016.
 */
public class ResultadosCategoriasAdapter extends RecyclerView.Adapter<ResultadosCategoriasAdapter.ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;
    List<ResultadosCategoriaVo> listaCategorias;
    View vista;

    public ResultadosCategoriasAdapter(List<ResultadosCategoriaVo> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resultados_card_list,parent,false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layParams);

        vista.setOnClickListener(this);

        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolder holderCategorias, int position) {
        holderCategorias.txtPuesto.setText((position+1)+"");
        holderCategorias.txtRegional.setText(listaCategorias.get(position).getRegional());
        holderCategorias.txtCentro.setText(listaCategorias.get(position).getNombreCentro());
        //holderCategorias.txtResDia1.setText(listaCategorias.get(position).getResultadoDia1()+"");
        //holderCategorias.txtResDia2.setText(listaCategorias.get(position).getResultadoDia2()+"");
        //holderCategorias.txtResDia3.setText(listaCategorias.get(position).getResultadoDia3()+"");
//        holderCategorias.txtResTotal.setText(listaCategorias.get(position).getResultadoTotal()+"");
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

        CardView cardViewResultados;
        TextView txtRegional;
        TextView txtCentro;
        TextView txtPuesto;
        TextView txtResDia1;
        TextView txtResDia2;
        TextView txtResDia3;
        TextView txtResTotal;

        public ViewHolder(View itemView) {
            super(itemView);
            cardViewResultados = (CardView)itemView.findViewById(R.id.cv);
            txtPuesto= (TextView) itemView.findViewById(R.id.txtPuesto);
            txtRegional= (TextView) itemView.findViewById(R.id.txtRegional);
            txtCentro= (TextView) itemView.findViewById(R.id.txtNombreCentro);
        //  txtResDia1= (TextView) itemView.findViewById(R.id.txtResultadoDia1);
        //  txtResDia2= (TextView) itemView.findViewById(R.id.txtResultadoDia2);
        //  txtResDia3= (TextView) itemView.findViewById(R.id.txtResultadoDia3);
        //    txtResTotal= (TextView) itemView.findViewById(R.id.txtResultadoTotal);
        }
    }
}
