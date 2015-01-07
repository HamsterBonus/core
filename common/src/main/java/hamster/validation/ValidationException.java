package hamster.validation;

import hamster.error.ApplicationException;

public class ValidationException extends ApplicationException {

    public ValidationException(String message) {
        super(message, "data.validation.error");
    }
}
