package az.company;

public class Valute {
    //fields
    private String code;
    private String nominal;
    private String name;
    private double value;

    //constructor
    public Valute(String code, String nominal, String name, double value) {
        this.code = code;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }
    //getters and setters

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Valute{" +
                "code='" + code + '\'' +
                ", nominal='" + nominal + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
