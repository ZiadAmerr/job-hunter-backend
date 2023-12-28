import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Worker extends User {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String bio;
    private byte[] picture;
    private byte[] cv;
    private List<Application> applications = new ArrayList<>();

    // Constructors
    public Worker(String username, String password, String firstName, String lastName,
                  String email, String phone, String bio, byte[] picture, byte[] cv) {
        super(username, password);

        Utils.checkName(firstName);
        this.firstName = firstName;

        Utils.checkName(lastName);
        this.lastName = lastName;

        Utils.checkEmail(email);
        this.email = email;

        Utils.checkPhone(phone);
        this.phone = phone;

        // No check for bio, picture, and cv
        this.bio = bio;
        this.picture = picture;
        this.cv = cv;
    }

    // Getters and Setters for worker-specific properties
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Utils.checkEmail(email);
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        Utils.checkPhone(phone);
        this.phone = phone;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Worker{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }

    public Application applyToJob(Job job) {
        Application app = new Application(this, job, new Date());
        applications.add(app);
        return app;
    }

    public Application applyToJob(int jobId) {
        Application app = new Application(this, jobId, new Date());
        applications.add(app);
        return app;
    }

    public boolean removeApplication(Application application) {
        if (applications.contains(application)) {
            applications.remove(application);
            Application.removeApplication(application.getId());
            return true;
        }
        return false;
    }

    public boolean removeApplication(int applicationId) {
        for (Application application : applications) {
            if (application.getId() == applicationId) {
                applications.remove(application);
                Application.removeApplication(applicationId);
                return true;
            }
        }
        return false;
    }

    public boolean removeApplication(Job job) {
        for (Application application : applications) {
            if (application.getJobId() == job.getId()) {
                applications.remove(application);
                Application.removeApplication(application.getId());
                return true;
            }
        }
        return false;
    }

    public List<Application> getApplications() {
        return applications;
    }
}