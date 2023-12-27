public class User {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String country;

    public User(String name, String email, String password, String phone, String country) {
        this.name = name;
        this.email = email;
        this.password = Utils.md5Hash(password);
        this.phone = phone;
        this.country = country;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean checkPassword(String password) {
        String hashedPass = Utils.md5Hash(password);
        return (hashedPass != null && (this.password == hashedPass || this.password.equals(hashedPass)));
    }

    public String getPhone() {
        return this.phone;
    }

    public String getCountry() {
        return this.country;
    }

    public static void main(String[] args) {
        User ziad = new User("Ziad", "ziad.amerr@yahoo.com", "123456", "123456789", "Egypt");
        System.out.println(ziad.getName());
        System.out.println(ziad.getEmail());
        System.out.println(ziad.checkPassword("123456"));
        System.out.println(ziad.checkPassword("1234567"));
        System.out.println(ziad.getPhone());
        System.out.println(ziad.getCountry());
    }
}
