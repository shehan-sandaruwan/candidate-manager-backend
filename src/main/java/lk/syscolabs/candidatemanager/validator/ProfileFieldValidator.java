package lk.syscolabs.candidatemanager.validator;

import lk.syscolabs.candidatemanager.model.Field;
import lk.syscolabs.candidatemanager.model.Profile;
import lk.syscolabs.candidatemanager.model.ProfileField;
import lk.syscolabs.candidatemanager.service.FieldService;
import lk.syscolabs.candidatemanager.service.ProfileFieldService;
import lk.syscolabs.candidatemanager.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileFieldValidator extends Validator {

    @Autowired
    private ProfileFieldService profileFieldService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private FieldService fieldService;

    @Override
    public ValidationResult validate(Object o) {

        ValidationResult validationResult = new ValidationResult();
        ProfileField profileField = (ProfileField) o;

        Profile profileByProfileId = profileField.getProfileByProfileId();
        Field fieldByFieldId = profileField.getFieldByFieldId();

        if (profileByProfileId == null) {
            validationResult.addError("profileByProfileId", "profileByProfileId cannot be empty.");
        } else if (profileService.getOne(profileByProfileId.getId()) == null) {
            validationResult.addError("profileByProfileId", "profileByProfileId isnt available.");
        }

        if (fieldByFieldId == null) {
            validationResult.addError("fieldByFieldId", "fieldByFieldId cannot be empty.");
        } else if (fieldService.getOne(fieldByFieldId.getId()) == null) {
            validationResult.addError("fieldByFieldId", "fieldByFieldId isnt available.");
        }

        if (!profileFieldService.findAllByFieldByFieldIdAndProfileByProfileId(fieldByFieldId, profileByProfileId).isEmpty()) {
            validationResult.addError("combination", "combination cannot be duplicated.");
        }

        return validationResult;
    }
}
