package exception;

import java.sql.SQLException;

public class MaxPoolSizeException extends Exception {
    public MaxPoolSizeException(String reason) {
        super(reason);
    }
}
