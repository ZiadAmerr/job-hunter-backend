import java.util.Date;

public class Job {
    private static int idCounter = 0;
    private final int id;
    private final int employerId;
    private String description;
    private String externalLink;
    private final Date postedDate;

    // Constructors
    public Job(int employerId, String description, String externalLink, Date postedDate) {
        this.id = idCounter++;
        this.employerId = employerId;
        this.description = description;
        this.externalLink = externalLink;
        this.postedDate = postedDate;
    }

    public Job(Employer employer, String description, String externalLink, Date postedDate) {
        this(employer.getId(), description, externalLink, postedDate);
    }

    // Getters and Setters for job-specific properties
    public int getId() {
        return id;
    }

    public int getEmployerId() {
        return employerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", employerId=" + employerId +
                ", description='" + description + '\'' +
                ", externalLink='" + externalLink + '\'' +
                ", postedDate=" + postedDate +
                '}';
    }
}
