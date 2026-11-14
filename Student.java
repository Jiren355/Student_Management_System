
import java.util.ArrayList;
import java.util.List;

/**
 * Subclass representing a university student.
 * Inherits from Person and implements abstract methods.
 */
public class Student extends Person {
    private String program;
    private int semester;
    private double cgpa;
    private final List<Course> enrolledCourses;

    public Student(String personId, String fullName, String email, String program, int semester, double cgpa) {
        super(personId, fullName, email); // Calls superclass constructor
        this.program = program;
        this.semester = semester;
        setCgpa(cgpa); // Uses setter for validation
        this.enrolledCourses = new ArrayList<>();
    }

    // Getters and Setters
    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        if (semester > 0) {
            this.semester = semester;
        }
    }

    public double getCgpa() {
        return cgpa;
    }

    public final void setCgpa(double cgpa) {
        if (cgpa >= 0.0 && cgpa <= 4.0) {
            this.cgpa = cgpa;
        } else {
            System.out.println("Invalid CGPA. Value must be between 0.00 and 4.00.");
        }
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    // Enrolls student in a course if not already enrolled
    public boolean enrollCourse(Course course) {
        for (Course c : enrolledCourses) {
            if (c.getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
                return false;
            }
        }
        enrolledCourses.add(course);
        return true;
    }

    // Determines academic standing based on CGPA
    public String getAcademicStanding() {
        if (cgpa >= 3.50) {
            return "Dean's List";
        } else if (cgpa >= 2.00) {
            return "Good Standing";
        } else {
            return "Academic Probation";
        }
    }

    @Override
    public String getRoleType() {
        return "Student";
    }

    @Override
    public void displayRoleDetails() {
        System.out.println("------------------------------------------------------------");
        System.out.printf("[%s] %s%n", getRoleType().toUpperCase(), getBasicInfo());
        System.out.printf("Program  : %s | Semester: %d%n", program, semester);
        System.out.printf("CGPA     : %.2f (%s)%n", cgpa, getAcademicStanding());
        System.out.print("Courses  : ");
        if (enrolledCourses.isEmpty()) {
            System.out.println("None enrolled");
        } else {
            for (int i = 0; i < enrolledCourses.size(); i++) {
                System.out.print(enrolledCourses.get(i).getCourseCode());
                if (i < enrolledCourses.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------------------");
    }
}