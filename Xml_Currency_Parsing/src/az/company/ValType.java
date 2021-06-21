package az.company;

public class ValType {
    //fields
    private String type;

    //constructor
    public ValType(String type) {
        this.type = type;
    }

    //getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ValType{" +
                "type='" + type + '\'' +
                '}';
    }
}
