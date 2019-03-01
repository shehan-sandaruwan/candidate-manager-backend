package lk.syscolabs.candidatemanager.validator;

import lk.syscolabs.candidatemanager.model.Department;
import lk.syscolabs.candidatemanager.model.DepartmentState;
import lk.syscolabs.candidatemanager.model.User;
import lk.syscolabs.candidatemanager.service.DepartmentService;
import lk.syscolabs.candidatemanager.service.StateService;
import lk.syscolabs.candidatemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentStateValidator extends Validator {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private StateService stateService;
    @Autowired
    private UserService userService;

    @Override
    public ValidationResult validate(Object o) {
        ValidationResult validationResult = new ValidationResult();
        DepartmentState departmentState = (DepartmentState) o;

        byte isActive = departmentState.getIsActive();
        Department departmentByDepartmentId = departmentState.getDepartmentByDepartmentId();
        User userByAssignedTo = departmentState.getUserByAssignedTo();

        if (isActive != 0 && isActive != 1) {
            validationResult.addError("isActive", "isActive should be either 0 or 1.");
        }

        if (departmentByDepartmentId == null) {
            validationResult.addError("departmentByDepartmentId", "departmentByDepartmentId cannot be empty");
        } else if (departmentService.getOne(departmentByDepartmentId.getId()) == null) {
            validationResult.addError("departmentByDepartmentId", "departmentByDepartmentId isnt available.");
        }

        if (userByAssignedTo == null) {
            validationResult.addError("userByAssignedTo", "userByAssignedTo cannot be empty.");
        } else if (userService.getOne(userByAssignedTo.getId()) == null) {
            validationResult.addError("userByAssignedTo", "userByAssignedTo isnt available.");
        }
        return validationResult;
    }
}
