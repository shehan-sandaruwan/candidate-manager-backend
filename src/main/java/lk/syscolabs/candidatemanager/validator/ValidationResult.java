package lk.syscolabs.candidatemanager.validator;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {

    private boolean hasError;
    private Map<String, String> errors;

    public ValidationResult() {
        this.errors = new HashMap<>();
        hasError = false;
    }

    public void addError(String field, String error) {
        this.hasError = true;
        this.errors.put(field, error);
    }

    public boolean hasError() {
        return hasError;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
