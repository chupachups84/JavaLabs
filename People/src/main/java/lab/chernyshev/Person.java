package lab.chernyshev;

import java.util.Date;

public class Person {
    private final int id;
    private final String name;
    private final String gender;
    private final Department department;
    private final int salary;
    private final Date birthday;

    public Person(int id, String name, String gender, Department department, int salary, Date birthday) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person { id = " + id +
                "; name = " + name +
                "; gender = " + gender +
                "; " + department.toString() +
                "; salary = " + salary +
                "; birthday = " + birthday +
                " }\n";
    }
}
