
public abstract class Person {
    
    private String personId;
    private String fullName;
    private String email;

    // Parameterized constructor
    public Person(String personId, String fullName, String email) {
        this.personId = personId;
        this.fullName = fullName;
        this.email = email;
    }

    // Public Getters and Setters
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  
    public String getBasicInfo() {
        return String.format("ID: %s | Name: %s | Email: %s", personId, fullName, email);
    }


    public abstract String getRoleType();

    public abstract void displayRoleDetails();
}