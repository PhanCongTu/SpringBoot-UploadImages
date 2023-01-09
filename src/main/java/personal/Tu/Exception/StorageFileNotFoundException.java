package personal.Tu.Exception;

import java.io.Serial;

public class StorageFileNotFoundException extends StorageException {
    @Serial
    private static final long serialVersionUID = 1L;

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
