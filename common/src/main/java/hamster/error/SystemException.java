package hamster.error;

public class SystemException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public SystemException(String message) {
        super(message, "system.error");
    }

}
