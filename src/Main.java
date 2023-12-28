import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Employer siemens = new Employer("siemens", "aA123456", "Siemens", "A company", null);
        Employer microsoft = new Employer("microsoft", "aA123456", "Microsoft", "A company", null);

        System.out.println(siemens.toString());
        System.out.println(microsoft.toString());

        Worker ziad = new Worker("ziad", "aA123456", "Ziad", "Khalil", "ziadamer@yahoo.com", "01000000000", "A bio", null, null);

        System.out.println(ziad.toString());

        Job job1 = new Job(siemens, "A job", "https://www.siemens.com", new Date());

        System.out.println(job1.toString());

        Job job2 = new Job(microsoft, "A job", "https://www.microsoft.com", new Date());

        System.out.println(job2.toString());

        System.out.println(ziad.getApplications());
        System.out.println(Application.getApplications());

        ziad.applyToJob(job1);

        System.out.println(ziad.getApplications());
        System.out.println(Application.getApplications());

        ziad.applyToJob(job2.getId());

        System.out.println(ziad.getApplications());
        System.out.println(Application.getApplications());

        ziad.removeApplication(0);

        System.out.println(ziad.getApplications());
        System.out.println(Application.getApplications());

        ziad.applyToJob(job1);

        System.out.println(ziad.getApplications());
        System.out.println(Application.getApplications());

        ziad.removeApplication(1);

        System.out.println(ziad.getApplications());
        System.out.println(Application.getApplications());

        ziad.removeApplication(job1);

        System.out.println(ziad.getApplications());
        System.out.println(Application.getApplications());
    }
}