package connection.pool;

import utils.PropertiesUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomConnectionPoolRunner {

    SimpleConnectionPool simpleConnectionPool;

    public CustomConnectionPoolRunner() {
        this.simpleConnectionPool = new SimpleConnectionPool(PropertiesUtils.url, PropertiesUtils.dbName, PropertiesUtils.dbUserName, PropertiesUtils.password);;
    }

    //    regularnie co np. minutę pool powinien być sprawdzany i nieaktywne połączenia powyżej startowego limitu (5-10), powinny być kasowane, żeby zwolnić zasoby

    public void run() {

    }

    private void checkIfConnectionIsInactive() throws SQLException {
        for(Connection connection: simpleConnectionPool.getConnectionPool()) {
            boolean isConnectionActive = connection.isValid(5);
            if(!isConnectionActive) {
                connection.close();
                if(SimpleConnectionPool.INITIAL_NUMBER_CONNECTION < 10) {
                simpleConnectionPool.createConnection();
                }
            }
//            sprawdza czy polaczenie jest aktywne przez 5 sekund czyli nie wykonuje zadnych operacji. jezeli nie wykonuje to zamyka
        }
    }

}
