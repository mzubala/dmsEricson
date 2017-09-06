package pl.com.bottega.dms.inside.model.commands;

public class CreateDocumentCommand implements Command {

    private String title;

    private Long employeeId;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public void validate(ValidationErrors errors) {
        if (title == null || title.trim().length() == 0)
            errors.add("title", "can't be blank");
        if(employeeId == null)
            errors.add("employeeId", "is required");
    }
}
