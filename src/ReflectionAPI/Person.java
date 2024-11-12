package ReflectionAPI;

public class Person {
    int id = 0;
    String name = "maks";
    String secondName = "Kusheev";
    String orientation = "getero";

    public void setId(int id) {
        this.id = id;
    }

    public Person(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public Person() {
    }

    public void setName(String name) {
        this.name = "dima";
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", orientation='" + orientation + '\'' +
                '}';
    }
}
