import javax.xml.xpath.XPathEvaluationResult;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSetMetaData;

public class DBExample {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "00000000";

    public static void main(String[] args) {
        // Load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Connect to the database
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {

            String query = "SELECT * FROM users";

            List<Map<String, Object>> list = executeQuery(connection, query);

            for (Map<String, Object> map : list) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }
                System.out.println();
                System.out.println();
            }

//            // Example: Insert data into the users table
////            insertUserData(connection, "Ziad", "Amer", "ziad.amerr@yahoo.com", "123456");
//
//            // Example: Query data from the users table
//            queryUserData(connection);
//
//            // Get all users
//            String sql = "SELECT * FROM users";
//            System.out.println(executeQuery(connection, sql));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, Object>> executeQuery(Connection connection, String sql) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            List<Map<String, Object>> resultList = new ArrayList<>();

            while (resultSet.next()) {
                Map<String, Object> resultMap = new HashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);

                    resultMap.put(columnName, value);
                }

                resultList.add(resultMap);
            }

            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or rethrow the exception if needed
        };

        return null;
    }
    public static boolean insertToTable(Connection connection, String table, String[] columns, String[] values) throws SQLException {
        String sql = "INSERT INTO " + table + " (";
        for (int i = 0; i < columns.length; i++) {
            sql += columns[i];
            if (i != columns.length - 1) {
                sql += ", ";
            }
        }
        sql += ") VALUES (";
        for (int i = 0; i < values.length; i++) {
            sql += "?";
            if (i != values.length - 1) {
                sql += ", ";
            }
        }
        sql += ")";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set values for the prepared statement
            for (int i = 0; i < values.length; i++) {
                statement.setString(i + 1, values[i]);
            }

            // Execute the SQL query
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void emptyTable(Connection connection, String table) throws SQLException {
        String sql = "DELETE FROM " + table;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertUserData(Connection connection, String fname, String lname, String email, String password) throws SQLException {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set values for the prepared statement
            String username = generateUsername(fname, lname); // You can implement your own logic to generate a username
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the SQL query
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User inserted successfully!");
            }
        }
    }

    private static ResultSet queryUserData(Connection connection) throws SQLException {
        String sql = "SELECT * FROM users";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            // Process the result set
            return resultSet;
        }
    }

    private static String generateUsername(String fname, String lname) {
        // Implement your own logic to generate a unique username based on the first and last name
        return fname.toLowerCase() + "_" + lname.toLowerCase();
    }
}
