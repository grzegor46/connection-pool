package connection.pool;

import utils.PropertiesUtils;

public class Main {
    public static void main(String[] args) {

        SimpleConnectionPool simpleConnectionPool = new SimpleConnectionPool(PropertiesUtils.url, PropertiesUtils.dbName, PropertiesUtils.dbUserName, PropertiesUtils.password);
    }
}