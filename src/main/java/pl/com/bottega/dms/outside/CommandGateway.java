package pl.com.bottega.dms.outside;

import pl.com.bottega.dms.inside.api.handlers.CreateDocumentHandler;
import pl.com.bottega.dms.inside.api.handlers.Handler;
import pl.com.bottega.dms.inside.api.handlers.VerifyDocumentHandler;
import pl.com.bottega.dms.inside.api.handlers.di.DocumentRepositoryAware;
import pl.com.bottega.dms.inside.model.DocumentRepository;
import pl.com.bottega.dms.inside.model.commands.Command;
import pl.com.bottega.dms.inside.model.commands.CreateDocumentCommand;
import pl.com.bottega.dms.inside.model.commands.VerifyDocumentCommand;
import pl.com.bottega.dms.outside.decorators.TransactionalHandler;
import pl.com.bottega.dms.outside.decorators.ValidatingHandler;
import pl.com.bottega.dms.outside.repositories.JPADocumentRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class CommandGateway {

    private Map<Class<? extends Command>, Class<? extends Handler>> handlersMap = new HashMap<>();

    private EntityManagerFactory entityManagerFactory;

    private static CommandGateway INSTANCE = new CommandGateway();

    private CommandGateway() {
        handlersMap.put(CreateDocumentCommand.class, CreateDocumentHandler.class);
        handlersMap.put(VerifyDocumentCommand.class, VerifyDocumentHandler.class);
        entityManagerFactory = Persistence.createEntityManagerFactory("DMS");
    }

    public <Result> Result execute(Command command) {
        Handler<Command, Result> handler = createHandler(command);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        injectDependencies(handler, entityManager);
        handler = decorateHandler(handler, entityManager);
        return handler.handle(command);
    }

    private <Result> Handler<Command, Result> createHandler(Command command) {
        Handler<Command, Result> handler;
        Class<? extends Handler> handlerClass = handlersMap.get(command.getClass());
        if (handlerClass == null) {
            throw new RuntimeException(String.format("No handler for %s registered", command.getClass()));
        }
        try {
            handler = handlerClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create handler", e);
        }
        return handler;
    }

    private <Result> void injectDependencies(Handler<Command, Result> handler, EntityManager entityManager) {
        if (handler instanceof DocumentRepositoryAware)
            ((DocumentRepositoryAware) handler).setDocumentRepository(createDocumentRepository(entityManager));

    }

    private DocumentRepository createDocumentRepository(EntityManager entityManager) {
        return new JPADocumentRepository(entityManager);
    }

    private <Result> Handler<Command, Result> decorateHandler(Handler<Command, Result> handler, EntityManager entityManager) {
        handler = new TransactionalHandler<>(handler, entityManager);
        handler = new ValidatingHandler<>(handler);
        return handler;
    }

    public static CommandGateway getInstance() {
        return INSTANCE;
    }

    public void close() {
        entityManagerFactory.close();
    }
}
