package entities;

public class PIBData {

    private int month;
    private Double year;
    private Double tax;

    public PIBData(int month, Double year, Double tax) {
        this.month = month;
        this.year = year;
        this.tax = tax;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Double getYear() {
        return year;
    }

    public void setYear(Double year) {
        this.year = year;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "PIBData [mes=" + month + ", ano=" + year + ", agropecuaria=" + tax + "]";
    }
}