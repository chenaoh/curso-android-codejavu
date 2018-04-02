package co.quindio.sena.senasoftquindio2016.clases.vo;

/**
 * Created by CHENAO on 30/09/2016.
 */
public class ResultadosCategoriaVo {

    private String categoria;
    private String regional;
    private String nombreCentro;
    private double resultadoDia1;
    private double resultadoDia2;
    private double resultadoDia3;
    private double resultadoTotal;

    public ResultadosCategoriaVo(String categoria, String regional, String nombreCentro, double resultadoDia1, double resultadoDia2, double resultadoDia3, double resultadoTotal) {
        this.categoria = categoria;
        this.regional = regional;
        this.nombreCentro = nombreCentro;
        this.resultadoDia1 = resultadoDia1;
        this.resultadoDia2 = resultadoDia2;
        this.resultadoDia3 = resultadoDia3;
        this.resultadoTotal = resultadoTotal;
    }

    public ResultadosCategoriaVo() {

    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public double getResultadoDia1() {
        return resultadoDia1;
    }

    public void setResultadoDia1(double resultadoDia1) {
        this.resultadoDia1 = resultadoDia1;
    }

    public double getResultadoDia2() {
        return resultadoDia2;
    }

    public void setResultadoDia2(double resultadoDia2) {
        this.resultadoDia2 = resultadoDia2;
    }

    public double getResultadoDia3() {
        return resultadoDia3;
    }

    public void setResultadoDia3(double resultadoDia3) {
        this.resultadoDia3 = resultadoDia3;
    }

    public double getResultadoTotal() {
        return resultadoTotal;
    }

    public void setResultadoTotal(double resultadoTotal) {
        this.resultadoTotal = resultadoTotal;
    }
}
