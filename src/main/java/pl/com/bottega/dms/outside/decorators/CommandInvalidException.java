package pl.com.bottega.dms.outside.decorators;

import pl.com.bottega.dms.inside.model.commands.ValidationErrors;

public class CommandInvalidException extends RuntimeException {

    private ValidationErrors validationErrors;

    public CommandInvalidException(ValidationErrors validationErrors) {
        this.validationErrors = validationErrors;
    }

    public ValidationErrors getValidationErrors() {
        return validationErrors;
    }
}
