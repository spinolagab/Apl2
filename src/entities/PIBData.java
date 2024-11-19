package entities;

public class PIBData {

    private String mes;
    private Double ano;
    private Double agropecuaria;

    public PIBData(String mes, Double ano, Double agropecuaria) {
        this.mes = mes;
        this.ano = ano;
        this.agropecuaria = agropecuaria;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Double getAno() {
        return ano;
    }

    public void setAno(Double ano) {
        this.ano = ano;
    }

    public Double getAgropecuaria() {
        return agropecuaria;
    }

    public void setAgropecuaria(Double agropecuaria) {
        this.agropecuaria = agropecuaria;
    }

    @Override
    public String toString() {
        return "PIBData [mes=" + mes + ", ano=" + ano + ", agropecuaria=" + agropecuaria + "]";
    }
}