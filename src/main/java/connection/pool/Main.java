package connection.pool;


import utils.PropertiesUtils;

public class Main {

    public static void main(String[] args) {

        CustomConnectionPoolRunner customConnectionPoolRunner = new CustomConnectionPoolRunner();
        customConnectionPoolRunner.run();
    }
}