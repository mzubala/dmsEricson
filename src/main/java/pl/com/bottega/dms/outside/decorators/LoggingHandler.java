package pl.com.bottega.dms.outside.decorators;

import pl.com.bottega.dms.inside.api.handlers.Handler;
import pl.com.bottega.dms.inside.model.commands.Command;

public class LoggingHandler<Cmd extends Command, Result> implements Handler<Cmd, Result> {

    private Handler<Command, Result> decorated;

    public LoggingHandler(Handler<Command, Result> decorated) {
        this.decorated = decorated;
    }

    @Override
    public Result handle(Cmd cmd) {
        long ts = System.currentTimeMillis();
        System.out.println("Handling command: " + cmd.toString());
        RuntimeException ex = null;
        try {
            Result r = decorated.handle(cmd);
            return r;
        } catch (RuntimeException e) {
            ex = e;
            throw e;
        }
        finally {
            long te = System.currentTimeMillis();
            String status = ex == null ? "OK" : "EXCEPTION";
            System.out.println(String.format("Handled command [%s]: %s (%d ms)", status, cmd, te - ts));
        }
    }
}
