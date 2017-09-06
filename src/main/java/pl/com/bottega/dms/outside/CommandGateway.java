package pl.com.bottega.dms.outside;

import pl.com.bottega.dms.inside.api.handlers.CreateDocumentHandler;
import pl.com.bottega.dms.inside.api.handlers.Handler;
import pl.com.bottega.dms.inside.model.commands.Command;
import pl.com.bottega.dms.inside.model.commands.CreateDocumentCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandGateway {

    private Map<Class<? extends Command>, Class<? extends Handler>> handlersMap = new HashMap<>();

    private CommandGateway() {
        handlersMap.put(CreateDocumentCommand.class, CreateDocumentHandler.class);
    }

    public <Result> Result execute(Command command) {
        Class<? extends Handler> handlerClass = handlersMap.get(command);
        if(handlerClass == null)
            throw new RuntimeException(String.format("No handler for %s registered", command.getClass()));
        Handler<Command, Result> handler = null;
        try {
            handler = handlerClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create handler", e);
        }

        injectDependencies(handler);
        decorateHandler(handler);

        return handler.handle(command);
    }

    private <Result> void injectDependencies(Handler<Command, Result> handler) {

    }

    private <Result> void decorateHandler(Handler<Command, Result> handler) {

    }

}
