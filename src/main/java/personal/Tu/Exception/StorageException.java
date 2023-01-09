package personal.Tu.Exception;

import java.io.Serial;

public class StorageException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Exception e) {
        super(message, e);
    }
}
