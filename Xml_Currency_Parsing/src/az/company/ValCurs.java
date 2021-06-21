package az.company;

public class ValCurs {

    //fields
    private String date;
    private String name;
    private String description;

    //getters and setters
    public String getDate() {
        return date;
    }

    //constructor
    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ValCurs{" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
