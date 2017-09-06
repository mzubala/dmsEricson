package pl.com.bottega.dms.inside.model.commands;

import java.util.HashMap;
import java.util.Map;

public class ValidationErrors {

    private Map<String, String> errors = new HashMap<>();

    public void add(String field, String error) {
        errors.put(field, error);
    }

    public boolean anyErrors() {
        return errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
