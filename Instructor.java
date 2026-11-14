public class Instructor extends Person {
    private String department;
    private String specialization;

    public Instructor(String personId, String fullName, String email, String department, String specialization) {
        super(personId, fullName, email);
        this.department = department;
        this.specialization = specialization;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String getRoleType() {
        return "Instructor";
    }

    @Override
    public void displayRoleDetails() {
        System.out.println("------------------------------------------------------------");
        System.out.printf("[%s] %s%n", getRoleType().toUpperCase(), getBasicInfo());
        System.out.printf("Department: %s | Specialization: %s%n", department, specialization);
        System.out.println("------------------------------------------------------------");
    }
}