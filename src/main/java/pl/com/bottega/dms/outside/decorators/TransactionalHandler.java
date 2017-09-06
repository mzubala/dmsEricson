package pl.com.bottega.dms.outside.decorators;

import pl.com.bottega.dms.inside.api.handlers.Handler;
import pl.com.bottega.dms.inside.model.commands.Command;

import javax.persistence.EntityManager;

public class TransactionalHandler<Cmd extends Command, Result> implements Handler<Cmd, Result> {

    private Handler<Command, Result> deocrated;
    private EntityManager entityManager;

    public TransactionalHandler(Handler<Command, Result> deocrated, EntityManager entityManager) {
        this.deocrated = deocrated;
        this.entityManager = entityManager;
    }

    @Override
    public Result handle(Cmd command) {
        entityManager.getTransaction().begin();
        try {
            Result r = deocrated.handle(command);
            entityManager.getTransaction().commit();
            entityManager.close();
            return r;
        }
        catch(RuntimeException ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        }
    }
}
