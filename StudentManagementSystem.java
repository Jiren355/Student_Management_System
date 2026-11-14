import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Console-based Student Management System demonstrating OOP fundamentals in Java.
 */
public class StudentManagementSystem {
    // Superclass reference list used to demonstrate Runtime Polymorphism
    private static final List<Person> people = new ArrayList<>();
    private static final List<Course> courses = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedSampleData();
        int choice;

        do {
            displayMenu();
            choice = readIntInput("Enter your choice (0-9): ");

            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    addNewInstructor();
                    break;
                case 3:
                    addNewCourse();
                    break;
                case 4:
                    enrollStudentInCourse();
                    break;
                case 5:
                    updateStudentCgpa();
                    break;
                case 6:
                    searchStudentById();
                    break;
                case 7:
                    displayAllStudents();
                    break;
                case 8:
                    displayAllCourses();
                    break;
                case 9:
                    demonstratePolymorphism();
                    break;
                case 0:
                    System.out.println("\nExiting Student Management System. Goodbye!");
                    break;
                default:
                    System.out.println("\n[Error] Invalid menu option. Please select 0 to 9.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void seedSampleData() {
        Instructor lec1 = new Instructor("LEC001", "Sir Nazmirul Izzad", "nazmirul@city.edu.my",
                "Faculty of Information Technology", "Object-Oriented Programming");
        Instructor lec2 = new Instructor("LEC002", "Dr. Amina Rahman", "amina@city.edu.my",
                "Faculty of Information Technology", "Cryptography & Cybersecurity");

        Course c1 = new Course("BIT1123", "Object Oriented Programming", 3, lec1);
        Course c2 = new Course("BIT4213", "Fundamentals of Cryptography", 3, lec2);

        Student s1 = new Student("202601010118", "Fahim Shahriar Mostofa", "shahriarmostofafahim@gmail.com",
                "Bachelor of Information Technology", 3, 3.66);
        s1.enrollCourse(c1);
        s1.enrollCourse(c2);

        Student s2 = new Student("202601010205", "Aisha Binti Zulkifli", "aisha.z@city.edu.my",
                "Bachelor of Software Engineering", 3, 3.82);
        s2.enrollCourse(c1);

        people.add(lec1);
        people.add(lec2);
        people.add(s1);
        people.add(s2);

        courses.add(c1);
        courses.add(c2);
    }

    private static void displayMenu() {
        System.out.println("\n============================================================");
        System.out.println("       CITY UNIVERSITY - STUDENT MANAGEMENT SYSTEM          ");
        System.out.println("============================================================");
        System.out.println("1. Add New Student");
        System.out.println("2. Add New Instructor");
        System.out.println("3. Add New Course");
        System.out.println("4. Enroll Student in a Course");
        System.out.println("5. Update Student CGPA");
        System.out.println("6. Search Student by ID");
        System.out.println("7. Display All Students");
        System.out.println("8. Display All Courses");
        System.out.println("9. Display All University Members (Runtime Polymorphism)");
        System.out.println("0. Exit");
        System.out.println("============================================================");
    }

    private static void addNewStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter Student ID   : ");
        String id = scanner.nextLine().trim();
        if (findPersonById(id) != null) {
            System.out.println("[Error] A person with ID " + id + " already exists.");
            return;
        }
        System.out.print("Enter Full Name    : ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Email        : ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Program      : ");
        String program = scanner.nextLine().trim();
        int semester = readIntInput("Enter Semester     : ");
        double cgpa = readDoubleInput("Enter CGPA (0-4.0) : ");

        Student newStudent = new Student(id, name, email, program, semester, cgpa);
        people.add(newStudent);
        System.out.println("[Success] Student added successfully!");
    }

    private static void addNewInstructor() {
        System.out.println("\n--- Add New Instructor ---");
        System.out.print("Enter Instructor ID  : ");
        String id = scanner.nextLine().trim();
        if (findPersonById(id) != null) {
            System.out.println("[Error] A person with ID " + id + " already exists.");
            return;
        }
        System.out.print("Enter Full Name      : ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Email          : ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Department     : ");
        String dept = scanner.nextLine().trim();
        System.out.print("Enter Specialization : ");
        String spec = scanner.nextLine().trim();

        Instructor newInstructor = new Instructor(id, name, email, dept, spec);
        people.add(newInstructor);
        System.out.println("[Success] Instructor added successfully!");
    }

    private static void addNewCourse() {
        System.out.println("\n--- Add New Course ---");
        System.out.print("Enter Course Code   : ");
        String code = scanner.nextLine().trim().toUpperCase();
        if (findCourseByCode(code) != null) {
            System.out.println("[Error] Course " + code + " already exists.");
            return;
        }
        System.out.print("Enter Course Name   : ");
        String name = scanner.nextLine().trim();
        int credits = readIntInput("Enter Credit Hours  : ");
        System.out.print("Enter Instructor ID : ");
        String lecId = scanner.nextLine().trim();

        Person p = findPersonById(lecId);
        Instructor assignedLec = (p instanceof Instructor) ? (Instructor) p : null;
        if (assignedLec == null) {
            System.out.println("[Info] Instructor ID not found; course assigned as 'TBA'.");
        }

        courses.add(new Course(code, name, credits, assignedLec));
        System.out.println("[Success] Course added successfully!");
    }

    private static void enrollStudentInCourse() {
        System.out.println("\n--- Enroll Student in Course ---");
        System.out.print("Enter Student ID  : ");
        String studentId = scanner.nextLine().trim();
        Person p = findPersonById(studentId);

        if (!(p instanceof Student)) {
            System.out.println("[Error] Student ID not found.");
            return;
        }

        System.out.print("Enter Course Code : ");
        String courseCode = scanner.nextLine().trim();
        Course course = findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("[Error] Course code not found.");
            return;
        }

        Student student = (Student) p;
        if (student.enrollCourse(course)) {
            System.out.println("[Success] " + student.getFullName() + " enrolled in " + course.getCourseCode() + ".");
        } else {
            System.out.println("[Info] Student is already enrolled in " + course.getCourseCode() + ".");
        }
    }

    private static void updateStudentCgpa() {
        System.out.println("\n--- Update Student CGPA ---");
        System.out.print("Enter Student ID : ");
        String studentId = scanner.nextLine().trim();
        Person p = findPersonById(studentId);

        if (!(p instanceof Student)) {
            System.out.println("[Error] Student ID not found.");
            return;
        }

        double newCgpa = readDoubleInput("Enter New CGPA (0.00 - 4.00): ");
        ((Student) p).setCgpa(newCgpa);
        System.out.println("[Success] Updated CGPA for " + p.getFullName() + ".");
    }

    private static void searchStudentById() {
        System.out.println("\n--- Search Student ---");
        System.out.print("Enter Student ID : ");
        String id = scanner.nextLine().trim();
        Person p = findPersonById(id);

        if (p instanceof Student) {
            p.displayRoleDetails();
        } else {
            System.out.println("[Not Found] No student matches ID: " + id);
        }
    }

    private static void displayAllStudents() {
        System.out.println("\n==================== REGISTERED STUDENTS ===================");
        boolean found = false;
        for (Person p : people) {
            if (p instanceof Student) {
                p.displayRoleDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No students registered.");
        }
    }

    private static void displayAllCourses() {
        System.out.println("\n===============================================================================");
        System.out.println("                              OFFERED COURSES                                  ");
        System.out.println("===============================================================================");
        for (Course c : courses) {
            c.displayCourseInfo();
        }
        System.out.println("===============================================================================");
    }

    // Demonstrates Runtime Polymorphism using Person superclass references
    private static void demonstratePolymorphism() {
        System.out.println("\n======= ALL UNIVERSITY MEMBERS (RUNTIME POLYMORPHISM) =======");
        for (Person person : people) {
            // Dynamic method dispatch invokes Student or Instructor displayRoleDetails() at runtime
            person.displayRoleDetails();
        }
    }

    private static Person findPersonById(String id) {
        for (Person p : people) {
            if (p.getPersonId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String code) {
        for (Course c : courses) {
            if (c.getCourseCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }

    private static int readIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[Error] Please enter a valid integer.");
            }
        }
    }

    private static double readDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[Error] Please enter a valid decimal number.");
            }
        }
    }
}