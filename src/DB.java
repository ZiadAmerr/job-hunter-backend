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

public class DB {

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

            insertEntry(connection, "users", Map.of(
                    "username", "ziad",
                    "password", "123456"
            ));

            String query = "SELECT * FROM users";

            List<Map<String, Object>> list = executeQuery(connection, query);

            for (Map<String, Object> map : list) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }
                System.out.println();
                System.out.println();
            }

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

    public static void insertEntry(Connection connection, String tableName, Map<String, Object> columnValues) throws SQLException {
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ")
                .append(tableName)
                .append(" (");

        // Append column names
        for (String columnName : columnValues.keySet()) {
            sqlBuilder.append(columnName).append(", ");
        }
        sqlBuilder.delete(sqlBuilder.length() - 2, sqlBuilder.length()); // Remove the last comma and space
        sqlBuilder.append(") VALUES (");

        // Append placeholders for values
        for (int i = 0; i < columnValues.size(); i++) {
            sqlBuilder.append("?, ");
        }
        sqlBuilder.delete(sqlBuilder.length() - 2, sqlBuilder.length()); // Remove the last comma and space
        sqlBuilder.append(")");

        try (PreparedStatement statement = connection.prepareStatement(sqlBuilder.toString())) {
            int parameterIndex = 1;
            for (Object value : columnValues.values()) {
                statement.setObject(parameterIndex++, value);
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or rethrow the exception if needed
        }
    }

    public static Object getEntryColumn(Connection connection, String tableName, String columnName, String columnToMatch, Object valueToMatch) throws SQLException {
        String sql = "SELECT " + columnName + " FROM " + tableName + " WHERE " + columnToMatch + " = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, valueToMatch);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getObject(columnName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int getId(Connection connection, String username) throws SQLException {
        String sql = "SELECT id FROM users WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
