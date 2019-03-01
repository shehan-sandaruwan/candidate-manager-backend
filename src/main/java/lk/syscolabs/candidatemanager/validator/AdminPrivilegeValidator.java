package lk.syscolabs.candidatemanager.validator;

import lk.syscolabs.candidatemanager.model.AdminPrivilege;
import lk.syscolabs.candidatemanager.model.User;
import lk.syscolabs.candidatemanager.service.AdminPrivilegeService;
import lk.syscolabs.candidatemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminPrivilegeValidator extends Validator {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminPrivilegeService adminPrivilegeService;

    @Override
    public ValidationResult validate(Object o) {
        ValidationResult validationResult = new ValidationResult();
        AdminPrivilege adminPrivilege = (AdminPrivilege) o;

        byte isAdmin = adminPrivilege.getIsAdmin();
        byte isSuperAdmin = adminPrivilege.getIsSuperAdmin();
        User user = adminPrivilege.getUserByUserId();

        if (isAdmin != 0 && isAdmin != 1) {
            validationResult.addError(ValidatorFields.IS_ADMIN, ValidatorFields.NOT_BOOLEAN);
        }
        if (isSuperAdmin != 0 && isSuperAdmin != 1) {
            validationResult.addError(ValidatorFields.IS_SUPER_ADMIN, ValidatorFields.NOT_BOOLEAN);
        }
        if (user == null) {
            validationResult.addError(ValidatorFields.USER_BY_USER_ID, ValidatorFields.EMPTY);
        } else if (userService.getOne(user.getId()) == null) {
            validationResult.addError(ValidatorFields.USER_BY_USER_ID, ValidatorFields.UNAVAILABLE);
        } else {
            List<AdminPrivilege> adminPrivileges = adminPrivilegeService.findAllByUserByUserId(userService.getOne(user.getId()));
            if (!adminPrivileges.isEmpty() && adminPrivileges.get(0).getId() != adminPrivilege.getId()) {
                validationResult.addError(ValidatorFields.USER_BY_USER_ID, ValidatorFields.DUPLICATE);
            }
        }
        return validationResult;
    }
}
