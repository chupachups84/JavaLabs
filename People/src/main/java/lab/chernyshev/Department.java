package lab.chernyshev;

public class Department {
    private final int id;
    private final String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department = { department.id = " + id + "; name = " + name + " }";
    }
}
