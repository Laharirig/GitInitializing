import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Empcallstmt {
    public static void main(String[] args) {
        // JDBC URL, username, and password
        String jdbcUrl = "jdbc:mysql://localhost:3306/Employee";
        String username = "root";
        String password = "root";

        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Call the stored procedure
            callStoredProcedure(connection);

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void callStoredProcedure(Connection connection) throws SQLException {
        // Prepare the stored procedure call
        String storedProcedureCall = "{CALL getEmployees()}";
        CallableStatement callableStatement = connection.prepareCall(storedProcedureCall);

        // Execute the stored procedure
        ResultSet resultSet = callableStatement.executeQuery();

        // Process the results
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name"));
        }

        // Close resources
        resultSet.close();
        callableStatement.close();
    }
}
