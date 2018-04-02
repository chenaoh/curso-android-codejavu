package co.quindio.sena.senasoftquindio2016.interfaces;

import co.quindio.sena.senasoftquindio2016.clases.vo.AgendaVo;
import co.quindio.sena.senasoftquindio2016.clases.vo.CategoriasVo;
import co.quindio.sena.senasoftquindio2016.clases.vo.EmpresaVo;
import co.quindio.sena.senasoftquindio2016.clases.vo.ResultadosVo;
import co.quindio.sena.senasoftquindio2016.clases.vo.TematicaCategoriaVo;

/**
 * Created by CHENAO on 6/08/2016.
 */
public interface IComunicaFragments {

    void enviaAgenda(AgendaVo agendaVo);

    void enviaCategoria(CategoriasVo categoriaVo);

    void enviaTematicaCategoria(TematicaCategoriaVo tematicaCategoriaVo);

    void enviaCategoriaResultado(ResultadosVo resultadosVo);
}
