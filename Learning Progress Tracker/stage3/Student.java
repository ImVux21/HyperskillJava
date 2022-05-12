package tracker;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private int[] points;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.points = new int[4];
    }
    
    public String getEmail() {
        return email;
    }

    public int[] getPoints() {
        return points;
    }
}
