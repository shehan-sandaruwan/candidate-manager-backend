package lk.syscolabs.candidatemanager.validator;

import lk.syscolabs.candidatemanager.model.Department;
import lk.syscolabs.candidatemanager.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentValidator extends Validator {
    @Autowired
    private DepartmentService departmentService;

    @Override
    public ValidationResult validate(Object o) {
        ValidationResult validationResult = new ValidationResult();
        Department department = (Department) o;

        String name = department.getName();

        if (name == null || name.length() == 0) {
            validationResult.addError("name", "name cannot be empty.");
        } else if (!departmentService.findAllByName(name).isEmpty() && departmentService.findAllByName(name).get(0).getId() != department.getId()) {
            validationResult.addError("name", "name cannot be duplicated.");
        }

        return validationResult;
    }
}
