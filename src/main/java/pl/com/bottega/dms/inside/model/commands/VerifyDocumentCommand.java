package pl.com.bottega.dms.inside.model.commands;

public class VerifyDocumentCommand implements Command {

    private Long employeeId;

    private String documentNumber;

    @Override
    public void validate(ValidationErrors validationErrors) {
        if(employeeId == null)
            validationErrors.add("employeeId", "is required");
        if(documentNumber == null)
            validationErrors.add("documentNumber", "is required");
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
