import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Application {
    private static int idCounter = 0;
    private final int id;
    private final int workerId;
    private final int jobId;
    private final Date applicationDate;
    private static List<Application> applications = new ArrayList<>();

    // Constructors
    public Application(int workerId, int jobId, Date applicationDate) {
        this.id = idCounter++;
        this.workerId = workerId;
        this.jobId = jobId;
        this.applicationDate = applicationDate;

        applications.add(this);
    }

    public Application(Worker worker, Job job, Date applicationDate) {
        this(worker.getId(), job.getId(), applicationDate);
    }

    public Application(int workerId, Job job, Date applicationDate) {
        this(workerId, job.getId(), applicationDate);
    }

    public Application(Worker worker, int jobId, Date applicationDate) {
        this(worker.getId(), jobId, applicationDate);
    }

    // Getters and Setters for application-specific properties
    public int getId() {
        return id;
    }

    public int getWorkerId() {
        return workerId;
    }

    public int getJobId() {
        return jobId;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public static List<Application> getApplications() {
        return applications;
    }

    public static boolean removeApplication(int id) {
        for (Application application : applications) {
            if (application.getId() == id) {
                applications.remove(application);
                return true;
            }
        }

        return false;
    }

    public static boolean removeApplication(Application application) {
        if (applications.contains(application)) {
            applications.remove(application);
            return true;
        }

        return false;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", workerId=" + workerId +
                ", jobId=" + jobId +
                ", applicationDate=" + applicationDate +
                '}';
    }
}
