package lk.syscolabs.candidatemanager.validator;

import lk.syscolabs.candidatemanager.model.User;
import lk.syscolabs.candidatemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator extends Validator {

    @Autowired
    private UserService userService;

    @Override
    public ValidationResult validate(Object o) {
        ValidationResult validationResult = new ValidationResult();
        User user = (User) o;

        String email = user.getEmail();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();

        if (email == null || email.length() == 0) {
            validationResult.addError(ValidatorFields.EMAIL, ValidatorFields.EMPTY);
        } else if (!isValidEmail(email)) {
            validationResult.addError(ValidatorFields.EMAIL, ValidatorFields.INVALID);
        }
        if (isValidEmail(email)) {
            email = email.toLowerCase();
            user.setEmail(user.getEmail().toLowerCase());
            if (!userService.findAllByEmail(email).isEmpty() && userService.findAllByEmail(email).get(0).getId() != user.getId()) {
                validationResult.addError(ValidatorFields.EMAIL, ValidatorFields.DUPLICATE);
            }
        }

        if (firstName == null || firstName.length() == 0) {
            validationResult.addError(ValidatorFields.FIRST_NAME, ValidatorFields.EMPTY);
        }

        if (lastName == null || lastName.length() == 0) {
            validationResult.addError(ValidatorFields.LAST_NAME, ValidatorFields.EMPTY);
        }

        return validationResult;
    }
}
