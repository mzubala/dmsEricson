package pl.com.bottega.dms.inside.model.commands;

public interface Command {

    void validate(ValidationErrors validationErrors);

}
