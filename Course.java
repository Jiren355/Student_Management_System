/**
 * Represents an academic course offered by the university.
 */
public class Course {
    private String courseCode;
    private String courseName;
    private int creditHours;
    private Instructor instructor;

    public Course(String courseCode, String courseName, int creditHours, Instructor instructor) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        setCreditHours(creditHours);
        this.instructor = instructor;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public final void setCreditHours(int creditHours) {
        if (creditHours > 0) {
            this.creditHours = creditHours;
        }
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void displayCourseInfo() {
        String lecturerName = (instructor != null) ? instructor.getFullName() : "TBA";
        System.out.printf("%-10s | %-34s | Credits: %d | Lecturer: %s%n",
                courseCode, courseName, creditHours, lecturerName);
    }
}