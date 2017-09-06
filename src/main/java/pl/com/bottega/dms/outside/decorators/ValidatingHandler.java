package pl.com.bottega.dms.outside.decorators;

import pl.com.bottega.dms.inside.api.handlers.Handler;
import pl.com.bottega.dms.inside.model.commands.Command;
import pl.com.bottega.dms.inside.model.commands.ValidationErrors;

public class ValidatingHandler<Cmd extends Command, Result> implements Handler<Cmd, Result> {

    private Handler<Cmd, Result> decorated;

    public ValidatingHandler(Handler<Cmd, Result> decorated) {
        this.decorated = decorated;
    }

    @Override
    public Result handle(Cmd cmd) {
        ValidationErrors validationErrors = new ValidationErrors();
        cmd.validate(validationErrors);
        if(validationErrors.anyErrors())
            throw new CommandInvalidException(validationErrors);
        return decorated.handle(cmd);
    }
}
