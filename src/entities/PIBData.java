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

    @Override
    public String toString() {
        return "PIBData [mes=" + mes + ", ano=" + ano + ", agropecuaria=" + agropecuaria + "]";
    }
}