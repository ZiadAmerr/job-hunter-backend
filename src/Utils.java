import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static String md5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array to a string representation
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found");
        }
    }

    public static void checkPassword(String password) {
        // Check if null
        if (password == null)
            throw new IllegalArgumentException("Password cannot be null");

        // Check if password is at least 8 characters long
        if (password.length() < 4)
            throw new IllegalArgumentException("Password must be at least 8 characters long");

        // Check if password contains at least one digit
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
                break;
            }
        }
        if (!hasDigit)
            throw new IllegalArgumentException("Password must contain at least one digit");

        // Check if password contains at least one lowercase letter
        boolean hasLowercase = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLowercase = true;
                break;
            }
        }
        if (!hasLowercase)
            throw new IllegalArgumentException("Password must contain at least one lowercase letter");

        // Check if password contains at least one uppercase letter
        boolean hasUppercase = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
                break;
            }
        }
        if (!hasUppercase)
            throw new IllegalArgumentException("Password must contain at least one uppercase letter");
    }

    public static void checkName(String name) {
        // Check if name is at least 2 characters long
        if (name.length() < 2)
            throw new IllegalArgumentException("Name must be at least 2 characters long");

        // Check if name contains only letters
        for (char c : name.toCharArray())
            if (!(Character.isLetter(c) || Character.isSpaceChar(c)))
                throw new IllegalArgumentException("Name must contain only letters and spaces");
    }

    public static void checkUsername(String username) {
        // Check if username is at least 4 characters long
        if (username.length() < 4)
            throw new IllegalArgumentException("Username must be at least 4 characters long");

        // Check if username contains only letters and digits
        for (char c : username.toCharArray()) {
            if (!Character.isLetterOrDigit(c))
                throw new IllegalArgumentException("Username must contain only letters and digits");
        }
    }

    public static void checkEmail(String email) {
        // Check if email contains @
        if (!email.contains("@") || email.indexOf("@") != email.lastIndexOf("@") || !email.contains(".") || email.indexOf("@") > email.indexOf("."))
            throw new IllegalArgumentException("Invalid email address");

        // Needs some work!
    }

    public static void checkPhone(String phone) {
        // Check if phone is at least 10 characters long
        if (phone.length() < 5)
            throw new IllegalArgumentException("Phone number must be at least 5 characters long");

        // Check if phone contains only digits
        for (char c : phone.toCharArray()) {
            if (!Character.isDigit(c))
                throw new IllegalArgumentException("Phone number must contain only digits");
        }
    }
}
