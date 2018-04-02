package co.quindio.sena.senasoftquindio2016.dialogos;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.clases.vo.TematicaCategoriaVo;
import co.quindio.sena.senasoftquindio2016.interfaces.IRegistroListener;

/**
 * Created by CHENAO on 6/08/2016.
 */
public class JornadaCategoriaDialog extends DialogFragment {

    /**Declaramos la interfaz para el envio de datos a la actividad contenedora*/
    IRegistroListener interfaceRegistroListener;

    TextView txtCategoria;
    TextView txtJornada;
    TextView txtDescripcion;
    Activity actividad;
    TematicaCategoriaVo tematicaCategoriaVo;

    public JornadaCategoriaDialog() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createLoginDialogo();
    }

    /**
     * Crea un diálogo con personalizado para comportarse
     * como formulario de login
     *
     * @return Diálogo
     */
    public AlertDialog createLoginDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View vista = inflater.inflate(R.layout.dialog_jornada_categoria, null);
        builder.setView(vista);

        ImageButton btnSalir = (ImageButton) vista.findViewById(R.id.btnSalir);
        txtCategoria=(TextView) vista.findViewById(R.id.textViewCategoria);
        txtJornada=(TextView) vista.findViewById(R.id.textViewJornada);
        txtDescripcion=(TextView) vista.findViewById(R.id.textDescripcion);

        //validamos si tenemos parametros
        Bundle parametro=getArguments();
        if (parametro!=null){
            tematicaCategoriaVo= (TematicaCategoriaVo) parametro.getSerializable("tematica");
            txtCategoria.setText(tematicaCategoriaVo.getNombreCategoria());
            txtJornada.setText(tematicaCategoriaVo.getJornada());
            txtDescripcion.setText(tematicaCategoriaVo.getDescripcion1());
        }

       // obtenerDia();
       // obtenerListaAgendaDia();

        btnSalir.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       dismiss();
                    }
                }
        );
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.actividad=(Activity) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


}
