package co.quindio.sena.senasoftquindio2016.adapters;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.clases.vo.ResultadosVo;

import static android.graphics.Color.BLACK;

/**
 * Created by CHENAO on 31/07/2016.
 */
public class ResultadosAdapter extends RecyclerView.Adapter<ResultadosAdapter.ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;
    List<ResultadosVo> listaCategorias;
    View vista;

    public ResultadosAdapter(List<ResultadosVo> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria_card_grid,parent,false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layParams);

        vista.setOnClickListener(this);

        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holderCategorias, int position) {
        holderCategorias.lugarImg.setImageResource(listaCategorias.get(position).getImagenId());
     //   holderCategorias.layout.setBackgroundColor(Color.GREEN);
      //  if (listaCategorias.get(position).getId()==2 || listaCategorias.get(position).getId()==10){
    //       holderCategorias.layout.setBackgroundColor(Color.RED);
    //    }

    /*    holderCategorias.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("**********************************************");
                System.out.println("**********************************************");
                ColorDrawable viewColor = (ColorDrawable) holderCategorias.layout.getBackground();
                int colorId = viewColor.getColor();

                System.out.println("Color Inicial: "+colorId);

                if (colorId==Color.RED){
                    System.out.println("La habitación que desea reservar en este momento se encuentra como no disponible: "+colorId);
                }else{
                    if (colorId==Color.GREEN){
                        holderCategorias.layout.setBackgroundColor(Color.YELLOW);
                    }else{
                        holderCategorias.layout.setBackgroundColor(Color.GREEN);
                    }
                }

                System.out.println("**********************************************");
            }
        });*/

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
        LinearLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            layout= (LinearLayout) itemView.findViewById(R.id.layoutCard);
            cv = (CardView)itemView.findViewById(R.id.cv);
            lugarImg=(ImageView) itemView.findViewById(R.id.imgVersion);
        }
    }
}
