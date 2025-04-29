package douglas.com.helpdesk.services.exceptions;

public class ObjectDuplicateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ObjectDuplicateException(String msg) {
        super(msg);
    }
    public ObjectDuplicateException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public ObjectDuplicateException(String msg, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
