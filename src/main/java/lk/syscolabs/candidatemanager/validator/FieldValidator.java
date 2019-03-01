package lk.syscolabs.candidatemanager.validator;

import lk.syscolabs.candidatemanager.model.Field;
import lk.syscolabs.candidatemanager.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FieldValidator extends Validator {

    @Autowired
    private FieldService fieldService;

    @Override
    public ValidationResult validate(Object o) {
        ValidationResult validationResult = new ValidationResult();
        Field field = (Field) o;

        String name = field.getName();
        String description = field.getDescription();

        if (name == null || name.length() == 0) {
            validationResult.addError("name", "name cannot be empty.");
        } else if (!fieldService.findAllByName(name).isEmpty() && fieldService.findAllByName(name).get(0).getId() != field.getId()) {
            validationResult.addError("name", "name cannot be duplicated.");
        }

        if (description == null || description.length() == 0) {
            validationResult.addError("description", "description cannot be empty.");
        }

        return validationResult;
    }
}
