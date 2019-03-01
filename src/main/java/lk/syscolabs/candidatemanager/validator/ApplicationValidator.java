package lk.syscolabs.candidatemanager.validator;

import lk.syscolabs.candidatemanager.model.Application;
import lk.syscolabs.candidatemanager.model.Position;
import lk.syscolabs.candidatemanager.service.ApplicationService;
import lk.syscolabs.candidatemanager.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationValidator extends Validator {
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private PositionService positionService;

    @Override
    public ValidationResult validate(Object o) {
        ValidationResult validationResult = new ValidationResult();
        Application application = (Application) o;

        String firstName = application.getFirstName();
        String lastName = application.getLastName();
        String nic = application.getNic();
        String institute = application.getInstitute();
        String gender = application.getGender();
        String email = application.getEmail();
        String contactNumber = application.getContactNumber();
        String cvName = application.getCvName();
        Position position = application.getPositionByPositionId();

        if (isEmpty(firstName)) {
            validationResult.addError(ValidatorFields.FIRST_NAME, ValidatorFields.EMPTY);
        }

        if (isEmpty(lastName)) {
            validationResult.addError(ValidatorFields.LAST_NAME, ValidatorFields.EMPTY);
        }

        if (isEmpty(nic)) {
            validationResult.addError(ValidatorFields.NIC, ValidatorFields.EMPTY);
        } else if (!oneOf(Character.toString(nic.charAt(nic.length() - 1)), new String[]{"v", "V"})) {
            validationResult.addError(ValidatorFields.NIC, ValidatorFields.INVALID);
        } else if (nic.length() != 10 || !onlyContainsNumbers(nic.substring(0, 9))) {
            validationResult.addError(ValidatorFields.NIC, ValidatorFields.INVALID);
        }

        if (isEmpty(institute)) {
            validationResult.addError(ValidatorFields.INSTITUTE, ValidatorFields.EMPTY);
        }

        if (isEmpty(gender)) {
            validationResult.addError(ValidatorFields.GENDER, ValidatorFields.EMPTY);
        } else if (!oneOf(gender, new String[]{"male", "female"})) {
            validationResult.addError(ValidatorFields.GENDER, ValidatorFields.INVALID);
        }

        if (isEmpty(email)) {
            validationResult.addError(ValidatorFields.EMAIL, ValidatorFields.EMPTY);
        } else if (!isValidEmail(email)) {
            validationResult.addError(ValidatorFields.EMAIL, ValidatorFields.INVALID);
        }

        if (isEmpty(contactNumber)) {
            validationResult.addError(ValidatorFields.CONTACT_NUMBER, ValidatorFields.EMPTY);
        } else if (!isValidContactNumber(contactNumber)) {
            validationResult.addError(ValidatorFields.CONTACT_NUMBER, ValidatorFields.INVALID);
        }

        if (isEmpty(cvName)) {
            validationResult.addError(ValidatorFields.CV_NAME, ValidatorFields.EMPTY);
        } else if (!applicationService.findAllByCvName(cvName).isEmpty()) {
            validationResult.addError(ValidatorFields.CV_NAME, ValidatorFields.DUPLICATE);
        }

        if (position == null) {
            validationResult.addError(ValidatorFields.POSITION_BY_POSITION_ID, ValidatorFields.EMPTY);
        } else if (positionService.getOne(position.getId()) == null) {
            validationResult.addError(ValidatorFields.POSITION_BY_POSITION_ID, ValidatorFields.UNAVAILABLE);
        }

        return validationResult;
    }
}
