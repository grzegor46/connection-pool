package exception;

import java.sql.SQLException;

public class MaxPoolSizeException extends RuntimeException {
    public MaxPoolSizeException(String reason) {
        super(reason);
    }
}
