package tracker;

import java.util.*;
import java.util.stream.Collectors;

public enum Course {
    JAVA("Java", 600),
    DSA("DSA", 400),
    DATABASES("Databases", 480),
    SPRING("Spring", 550);

    private final String name;
    private final int certainPoints;
    private int studentSubmission;
    private int totalPoint;
    private final List<Student> enrolledStudentList;
    public static final Course[] COURSES = Course.values();

    Course(String name, int certainPoints) {
        this.name = name;
        this.certainPoints = certainPoints;
        this.studentSubmission = 0;
        this.totalPoint = 0;
        this.enrolledStudentList = new ArrayList<>();
    }

    public void addEnrolledStudentIfNotYet(Student student) {
        if (!enrolledStudentList.contains(student)) {
            enrolledStudentList.add(student);
        }
    }

    public void totalPoint(int point) {
        totalPoint += point;
    }
    public void addSubmission() {
        studentSubmission++;
    }

    private double getAvaragePoint() {
        if (studentSubmission != 0) {
            return (double) totalPoint / studentSubmission;
        }

        return 0.0;
    }

    public static void calculateStatisticsCommand() {
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;

        System.out.println("Type the name of a course to see details or 'back' to quit:");
        showPropertiesEachCourses();

        while(!stop) {
            String courseName = scanner.nextLine();
            if (courseName.equals("back")) {
                stop = true;
            } else {
                switch (courseName.toLowerCase()) {
                    case "java":
                        showDetailsEachCourses(COURSES[0]);
                        break;
                    case "dsa":
                        showDetailsEachCourses(COURSES[1]);
                        break;
                    case "databases":
                        showDetailsEachCourses(COURSES[2]);
                        break;
                    case "spring":
                        showDetailsEachCourses(COURSES[3]);
                        break;
                    default:
                        System.out.println("Unknown course.");
                }
            }
        }
    }

    private static boolean hasAvailableData() {
        for (Course course : values()) {
            if (course.enrolledStudentList.size() > 0) {
                return true; // if there is at least a course having enrolled student
            }
        }
        return false;
    }

    private static void showDetailsEachCourses(Course course) {
        sortListStudentEnrolled(course);
        System.out.println(course.name);
        System.out.println("id    points    completed");
        for (Student student : course.enrolledStudentList) {
            String rateCompletedCourse = String.valueOf(getRateCompletedCourse(student, course)).substring(0, 3); // remove redundant zeroes
            System.out.printf(
                    "%-5d %-6d    %s%%\n",
                    student.getId(), student.getPoints()[course.ordinal()],
                    rateCompletedCourse);
        }
    }

    private static void sortListStudentEnrolled(Course course) {
        Comparator<Student> sortById = Comparator.comparingInt(Student::getId);
        Comparator<Student> sortByTotalPoints = (student1, student2) -> Integer.compare(student2.getPoints()[course.ordinal()], student1.getPoints()[course.ordinal()]);

        course.enrolledStudentList.sort(sortById);
        course.enrolledStudentList.sort(sortByTotalPoints);
    }

    private static double getRateCompletedCourse(Student student, Course course) {
        double rateCompleted = (double) student.getPoints()[course.ordinal()] / course.certainPoints;
        return Math.round((rateCompleted * 100) * 10.0) / 10.0;
    }

    private static void showPropertiesEachCourses() {
        // TODO: edit the order of the method calls
        if (!hasAvailableData()) {
            System.out.println("Most popular: n/a\n" +
                    "Least popular: n/a\n" +
                    "Highest activity: n/a\n" +
                    "Lowest activity: n/a\n" +
                    "Easiest course: n/a\n" +
                    "Hardest course: n/a");
        } else {
            System.out.printf("Most popular: %s\n" +
                            "Least popular: %s\n" +
                            "Highest activity: %s\n" +
                            "Lowest activity: %s\n" +
                            "Easiest course: %s\n" +
                            "Hardest course: %s\n"
                    , getMostPopular(), getLeastPopular(), getHighestActivities()
                    , getLowestActivities(), getEasiestCourse(), getHardestCourse());
        }
    }

    private static double getLowestAvaragePoint() {
        return Arrays.stream(COURSES)
                .filter(course -> course.enrolledStudentList.size() > 0)
                .map(Course::getAvaragePoint)
                .min(Double::compareTo)
                .get();
    }

    private static String getHardestCourse() {
        double lowestAvaragePoint = getLowestAvaragePoint();

        List<String> hardestCourse = Arrays.stream(COURSES)
                .filter(course -> course.enrolledStudentList.size() > 0)
                .filter(course -> course.getAvaragePoint() == lowestAvaragePoint)
                .map(course -> course.name)
                .filter(name -> !getEasiestCourseList().contains(name))
                .collect(Collectors.toList());
        if (hardestCourse.size() > 0) {
            return String.join(", ", hardestCourse);
        }
        return "n/a";
    }

    private static double getHighestAvaragePoint() {
        return Arrays.stream(COURSES)
                .filter(course -> course.enrolledStudentList.size() > 0)
                .map(Course::getAvaragePoint)
                .max(Double::compareTo)
                .get();
    }

    private static String getEasiestCourse() {
        if (getEasiestCourseList().size() > 0) {
            return String.join(", ", getEasiestCourseList());
        }
        return "n/a";
    }

    private static List<String> getEasiestCourseList() {
        double highestAvaragePoint = getHighestAvaragePoint();

        return Arrays.stream(COURSES)
                .filter(course -> course.enrolledStudentList.size() > 0)
                .filter(course -> course.getAvaragePoint() == highestAvaragePoint)
                .map(course -> course.name)
                .collect(Collectors.toList());
    }

    private static List<String> getHighestActivitiesList() {
        int highestSubmission = getHighestSubmission();

        return Arrays.stream(COURSES)
                .filter(course -> course.studentSubmission > 0) // remove all the courses having students
                .filter(course -> course.studentSubmission == highestSubmission) // remove all courses having less students
                .map(course -> course.name)
                .collect(Collectors.toList());
    }

    private static int getLowestSubmission() {
        return Arrays.stream(COURSES)
                .map(course -> course.studentSubmission)
                .min(Integer::compare)
                .get();
    }

    private static int getHighestSubmission() {
        return Arrays.stream(COURSES)
                .map(course -> course.studentSubmission)
                .max(Integer::compare)
                .get();
    }

    private static String getHighestActivities() {
        if (getHighestActivitiesList().size() > 0) {
            return String.join(", ", getHighestActivitiesList());
        }
        return "n/a";
    }

    private static String getLowestActivities() {
        int lowestSubmission = getLowestSubmission();
        List<String> lowestActivities = Arrays.stream(COURSES)
                .filter(course -> course.studentSubmission > 0) // remove all the courses having students
                .filter(course -> course.studentSubmission == lowestSubmission) // remove all courses having less students
                .map(course -> course.name)
                .filter(name -> !getHighestActivitiesList().contains(name))
                .collect(Collectors.toList());
        if (lowestActivities.size() > 0) {
            return String.join(", ", lowestActivities);
        }
        return "n/a";
    }

    private static String getLeastPopular() {
        int leastEnrolledCourse = getLeastEnrollCourse();

        List<String> leastPopular = Arrays.stream(COURSES)
                .filter(course -> course.enrolledStudentList.size() == leastEnrolledCourse)
                .map(course -> course.name)
                .filter(name -> !getMostPopularList().contains(name))
                .collect(Collectors.toList());
        if (leastPopular.size() > 0) {
            return String.join(", ", leastPopular);
        }
        return "n/a";
    }

    private static int getLeastEnrollCourse() {
        return Arrays.stream(COURSES)
                .map(course -> course.enrolledStudentList.size())
                .min(Integer::compareTo)
                .get();
    }

    private static String getMostPopular() {
        if (getMostPopularList().size() > 0) {
            return String.join(", ", getMostPopularList());
        }
        return "n/a";
    }

    private static List<String> getMostPopularList() {
        int mostEnrolledCourse = getMostEnrolledCourse();

        List<String> mostPopular = Arrays.stream(COURSES)
                .filter(course -> course.enrolledStudentList.size() == mostEnrolledCourse)
                .map(course -> course.name)
                .collect(Collectors.toList());

        return mostPopular;
    }

    private static int getMostEnrolledCourse() {
        return Arrays.stream(COURSES)
                .map(course -> course.enrolledStudentList.size())
                .max(Integer::compareTo)
                .get();
    }

    public static void notifyCompletedCourseCommand() {
        Set<Student> studentCompletedCourseSet = studentCompletedCourseSet();
        if (studentCompletedCourseSet.size() == 0) {
            System.out.println("Total 0 students have been notified.");
        } else {
            for (Student student : studentCompletedCourseSet) {
                for (Course course : student.getNotifyCompletedCourse()) {
                    System.out.printf("To: %s\n" +
                                    "Re: Your Learning Progress\n" +
                                    "Hello, %s %s! You have accomplished our %s course!\n",
                            student.getEmail(),
                            student.getFirstName(), student.getLastName(),
                            course.name);
                    student.setNotifiedYet(true); // if notified
                }
            }
            System.out.printf("Total %d students have been notified.\n", studentCompletedCourseSet.size());
        }
    }

    private static Set<Student> studentCompletedCourseSet() {
       Set<Student> studentCompletedCourseSet = new HashSet<>(); // avoid duplicated Student

        for (Course course : COURSES) {
            for (Student student : course.enrolledStudentList) {
                if (getRateCompletedCourse(student, course) == 100.0 && !student.isNotifiedYet()) {
                    student.getNotifyCompletedCourse().add(course);
                    studentCompletedCourseSet.add(student);
                }
            }
        }
        return studentCompletedCourseSet;
    }
}
