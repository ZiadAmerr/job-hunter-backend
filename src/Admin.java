public class Admin extends User {
    private String role;
    private String firstName;
    private String lastName;

    // Constructors
    public Admin(String username, String password, String role, String firstName, String lastName) {
        super(username, password);

        checkRole(role);
        this.role = role;

        Utils.checkName(firstName);
        this.firstName = firstName;

        Utils.checkName(lastName);
        this.lastName = lastName;
    }

    // Getters and Setters for admin-specific properties
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        checkRole(role);
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        Utils.checkName(firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        Utils.checkName(lastName);
        this.lastName = lastName;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", role='" + role + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    private void checkRole(String role) {
        String[] roles = {"admin", "employer", "worker"};

        for (String r : roles)
            if (r.equals(role))
                return;

        throw new IllegalArgumentException("Invalid role");
    }
}