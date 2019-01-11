package in.ma.apis.db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author Bharath
 */
public class Connector implements AutoCloseable {

    private Connection connection;

    public Connector() throws ConnectionNotFound {
        try {
            InitialContext ic = new InitialContext();
            Context xmlContext = (Context) ic.lookup("java:comp/env");
            DataSource myDatasource = (DataSource) xmlContext.lookup("jdbc/MyDatasource");
            connection = myDatasource.getConnection();
        } catch (SQLException | NamingException e) {
            throw new ConnectionNotFound(e.getMessage());
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
        }
    }
}

class ConnectionNotFound extends Exception {

    public ConnectionNotFound() {
        super("Failed to Open Database Connection");
    }

    public ConnectionNotFound(String message) {
        super("Failed to Open Database Connection\n" + message);
    }

}
