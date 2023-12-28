public abstract class User {
    private static int idCounter = 1;
    private final int id;
    private String username;
    private String password;  // Store the hashed password

    // Constructors`
    private User() {
        this.id = idCounter++;
    }

    protected User(String username, String password) {
        this();

        Utils.checkUsername(username);
        this.username = username;

        Utils.checkPassword(password);
        this.password = Utils.md5Hash(password);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Utils.checkUsername(username);
        this.username = username;
    }

    // Hash the password using md5Hash from Utils
    public void setPassword(String password) {
        Utils.checkPassword(password);
        this.password = Utils.md5Hash(password);
    }

    // ToDo: Unsafe, remove!
    public String getPassword() {
        return password;
    }

    // Check if a given plaintext password matches the stored hash
    public boolean checkPassword(String password) {
        Utils.checkPassword(password);
        return Utils.md5Hash(password).equals(password);
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
