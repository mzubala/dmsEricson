package pl.com.bottega.dms;

import pl.com.bottega.dms.inside.model.commands.CreateDocumentCommand;
import pl.com.bottega.dms.inside.model.commands.VerifyDocumentCommand;
import pl.com.bottega.dms.outside.CommandGateway;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        CreateDocumentCommand cmd = new CreateDocumentCommand();
        cmd.setTitle("Pierwszy dokument");
        cmd.setEmployeeId(666L);
        String number = CommandGateway.getInstance().execute(cmd);
        System.out.println("Numer doca: " + number);
        VerifyDocumentCommand verifyCmd = new VerifyDocumentCommand();
        verifyCmd.setDocumentNumber(number);
        verifyCmd.setEmployeeId(777L);
        CommandGateway.getInstance().execute(verifyCmd);
        CommandGateway.getInstance().close();
    }
}
