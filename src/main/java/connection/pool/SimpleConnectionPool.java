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

    public SimpleConnectionPool(String urlDb, String dataBaseName, String userNameDb, String passwordDb) throws MaxPoolSizeException {
        String URL = String.format(urlDb, dataBaseName);
        for(int i=0; i <= INITIAL_NUMBER_CONNECTION; i++) {
            Connection connection = createConnection(URL, userNameDb, passwordDb);
            connectionPool.add(connection);
        }
    }

    public Connection getConnectionFromPool() {
        Connection connection = connectionPool.remove(connectionPool.size()-1);
        usedConnections.add(connection);
        return connection;
    }

    public Connection createConnection(String URL, String userNameDb, String passwordDb) throws MaxPoolSizeException {
        try {
            if (connectionPool.size() <= MAX_AVAILABLE_NUMBER_CONNECTIONS) {
                return DriverManager.getConnection(URL, userNameDb, passwordDb);

            }
        }catch(SQLException e) {
            throw new MaxPoolSizeException("No available connections in the pool");
        }

        return null;
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
