public class Employer extends User {
    private String name;
    private String description;
    private byte[] picture;

    // Constructor
    public Employer(String username, String password, String name, String description, byte[] picture) {
        super(username, password);

        Utils.checkName(name);
        this.name = name;

        // No check
        this.description = description;

        // No check
        this.picture = picture;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        Utils.checkName(name);
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        // No check
        this.description = description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        // No check
        this.picture = picture;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Employer{" +
                "id=" + getId() +   // Accessing the id from the User superclass
                ", username='" + getUsername() + '\'' +  // Accessing the username from the User superclass
                ", password='" + getPassword() + '\'' +  // Accessing the password from the User superclass
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
