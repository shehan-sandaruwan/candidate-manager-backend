package lk.syscolabs.candidatemanager.validator;

import lk.syscolabs.candidatemanager.model.Position;
import lk.syscolabs.candidatemanager.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PositionValidator extends Validator {
    @Autowired
    private PositionService positionService;

    @Override
    public ValidationResult validate(Object o) {
        ValidationResult validationResult = new ValidationResult();
        Position position = (Position) o;

        int isOpen = position.getIsOpen();
        String name = position.getName();
        String description = position.getDescription();
        String department = position.getDepartment();


        if (isOpen <0) {
            validationResult.addError("isOpen", "isOpen should be either 0 or 1.");
        }

        if (name == null || name.length() == 0) {
            validationResult.addError("name", "name cannot be empty.");
        } else if (!positionService.findAllByName(name).isEmpty() && positionService.findAllByName(name).get(0).getId() != position.getId()) {
            validationResult.addError("name", "name cannot be duplicated.");
        }

        if (description == null || description.length() == 0) {
            validationResult.addError("description", "description cannot be empty.");
        }
        if (department == null || department.length() == 0) {
            validationResult.addError("department", "department cannot be empty.");
        }



        return validationResult;

    }
}
