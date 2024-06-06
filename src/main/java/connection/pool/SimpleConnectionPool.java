package connection.pool;

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
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    final static int MAX_AVAILABLE_NUMBER_CONNECTIONS = 100;
    final static int INITIAL_NUMBER_CONNECTION = 10;

    public SimpleConnectionPool(String urlDb, String dataBaseName, String userNameDb, String passwordDb)  {
        String URL = String.format(urlDb, dataBaseName);
        try {
            Connection connection = DriverManager.getConnection(URL, userNameDb, passwordDb);
            for(int i=0; i <= INITIAL_NUMBER_CONNECTION; i++) {
            connectionPool.add(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size()-1);
        usedConnections.add(connection);
        return connection;
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
