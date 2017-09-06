package pl.com.bottega.dms.inside.api.handlers;

import pl.com.bottega.dms.inside.model.commands.Command;

public interface Handler<Cmd extends Command, Result> {

    Result handle(Cmd cmd);

}
