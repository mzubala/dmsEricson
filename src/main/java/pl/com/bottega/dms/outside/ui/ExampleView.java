package pl.com.bottega.dms.outside.ui;

import pl.com.bottega.dms.inside.model.commands.CreateDocumentCommand;
import pl.com.bottega.dms.outside.CommandGateway;

//przykładowy widok z vadina ;)
public class ExampleView {

    public void onClick() {
        CreateDocumentCommand createDocumentCommand = new CreateDocumentCommand();
        createDocumentCommand.setTitle("tytuł pobrany z kompenentu gui");
        String docuemntNumber = CommandGateway.getInstance().execute(createDocumentCommand);

    }

}
