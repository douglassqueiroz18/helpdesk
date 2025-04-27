package douglas.com.helpdesk.services.exceptions;

public class ObjectnotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ObjectnotFoundException(String msg) {
        super(msg);
    }

    public ObjectnotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public ObjectnotFoundException(String msg, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }    
}
