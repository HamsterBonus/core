package hamster.validation;

import hamster.error.ApplicationException;

public class ValidationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ValidationException(String message) {
        super(message, "data.validation.error");
    }
}
