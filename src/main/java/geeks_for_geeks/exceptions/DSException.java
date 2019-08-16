package geeks_for_geeks.exceptions;

/**
 * Created By: Prashant Chaubey
 * Created On: 27-01-2019 19:15
 * Purpose: TODO:
 **/
public class DSException extends RuntimeException {
    public DSException() {
    }

    public DSException(String message) {
        super(message);
    }

    public DSException(String message, Throwable cause) {
        super(message, cause);
    }

    public DSException(Throwable cause) {
        super(cause);
    }

    public DSException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
