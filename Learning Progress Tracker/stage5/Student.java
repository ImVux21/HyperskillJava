package tracker;

import java.util.LinkedList;
import java.util.List;

public class Student {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final List<Course> notifyCompletedCourse;
    private boolean notifiedYet;
    private final int[] points;


    public Student(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.notifiedYet = false;
        this.notifyCompletedCourse = new LinkedList<>();
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

    public void setNotifiedYet(boolean notifiedYet) {
        this.notifiedYet = notifiedYet;
    }

    public boolean isNotifiedYet() {
        return notifiedYet;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Course> getNotifyCompletedCourse() {
        return notifyCompletedCourse;
    }
}
