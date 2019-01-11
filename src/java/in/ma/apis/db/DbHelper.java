package in.ma.apis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Bharath
 */
public class DbHelper implements AutoCloseable {

    private Connector connector;
    private Connection connection;

    public DbHelper() throws ConnectionNotFound {
        connector = new Connector();
        connection = connector.getConnection();
    }

    public void registerUser(
            String userId,
            String name,
            String emailId,
            long mobileNumber,
            String password
    ) throws SQLException, OperationFailed {
        try (PreparedStatement ps = connection.prepareStatement(""
                + "INSERT INTO u_users ("
                + "USER_ID, "
                + "NAME, "
                + "EMAIL_ID, "
                + "MOBILE_NUMBER, "
                + "PASSWORD"
                + "VALUES(?,?,?,?,?)"
                + ")")) {
            ps.setString(1, userId);
            ps.setString(2, name);
            ps.setString(3, emailId);
            ps.setLong(4, mobileNumber);
            ps.setString(5, password);
            if (ps.executeUpdate() == 0) {
                throw new OperationFailed("Error inserting row into u_users{TABLE}");
            }
        } catch (SQLException | OperationFailed e) {
            throw e;
        }

    }

    @Override
    public void close() throws Exception {
        connector.close();
    }

}

class OperationFailed extends Exception {

    public OperationFailed() {
        super("Operation failed!.");
    }

    public OperationFailed(String message) {
        super(message);
    }

}
