package tracker;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int[] points;

    public Student(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.points = new int[4];
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int[] getPoints() {
        return points;
    }
}
