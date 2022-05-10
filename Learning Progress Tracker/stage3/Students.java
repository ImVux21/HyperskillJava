package tracker;

public class Students {
    String fullName;
    String email;
    int[] points;

    public String getEmail() {
        return email;
    }

    public Students(String fullName, String email, int[] points) {
        this.fullName = fullName;
        this.email = email;
        this.points = points;
    }

    public void setPoints(int[] points) {
        this.points = points;
    }

    public int[] getPoints() {
        return points;
    }
}
