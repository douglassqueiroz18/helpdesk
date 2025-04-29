package douglas.com.helpdesk.services.exceptions;

public class DataIntegrityViolationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataIntegrityViolationException(String msg) {
        super(msg);
    }

    public DataIntegrityViolationException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public DataIntegrityViolationException(String msg, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }    
}
