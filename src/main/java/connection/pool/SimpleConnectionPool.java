package connection.pool;

import exception.MaxPoolSizeException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleConnectionPool {

    private String urlDb;
    private String userNameDb;
    private String passwordDb;
    private String dataBaseName;
    private List<Connection> connectionPool = new ArrayList<>();;
    private List<Connection> usedConnections = new ArrayList<>();
    final static int MAX_AVAILABLE_NUMBER_CONNECTIONS = 100;
    final static int INITIAL_NUMBER_CONNECTION = 10;

    public SimpleConnectionPool(String urlDb, String dataBaseName, String userNameDb, String passwordDb) {
        this.urlDb = urlDb;
        this.dataBaseName = dataBaseName;
        this.userNameDb = userNameDb;
        this.passwordDb = passwordDb;

        for(int i=0; i <= INITIAL_NUMBER_CONNECTION; i++) { // allokowanie 10 polaczen do bazy
            Connection connection = createConnection();
            connectionPool.add(connection);
        }
    }

    public Connection getConnectionFromPool() {
        Connection connection = connectionPool.remove(connectionPool.size()-1);
        usedConnections.add(connection);
        return connection;
    }

    public Connection createConnection() {
        try {
            if (connectionPool.size() <= MAX_AVAILABLE_NUMBER_CONNECTIONS) {
                String url = String.format(this.urlDb, this.dataBaseName);
                return DriverManager.getConnection(url, this.userNameDb, this.passwordDb);
            }
        }catch(SQLException e) {
            throw new MaxPoolSizeException("No available connections in the pool- max 100 reached"); // limit polaczen, rzuci wyjatek ze nie mozna wiecej niz 100
        }

        return null;
    }

    public void releaseConnection() {
        Connection connection = usedConnections.remove(usedConnections.size()-1);
        connectionPool.add(connection);
    }

    public List<Connection> getConnectionPool() {
        return connectionPool;
    }

    public String getUrlDb() {
        return urlDb;
    }

    public String getUserNameDb() {
        return userNameDb;
    }

    public String getPasswordDb() {
        return passwordDb;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public List<Connection> getUsedConnections() {
        return usedConnections;
    }
}
