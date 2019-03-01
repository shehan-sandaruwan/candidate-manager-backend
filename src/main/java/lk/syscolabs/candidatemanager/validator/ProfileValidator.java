package lk.syscolabs.candidatemanager.validator;

import lk.syscolabs.candidatemanager.model.Profile;
import lk.syscolabs.candidatemanager.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileValidator extends Validator {

    @Autowired
    private ProfileService profileService;

    @Override
    public ValidationResult validate(Object o) {
        ValidationResult validationResult = new ValidationResult();
        Profile profile = (Profile) o;

        String name = profile.getName();
        String description = profile.getDescription();

        if (name == null || name.length() == 0) {
            validationResult.addError("name", "name cannot be empty.");
        } else if (!profileService.findAllByName(name).isEmpty() && profile.getId() != profileService.findAllByName(name).get(0).getId()) {
            validationResult.addError("name", "name cannot be duplicated.");
        }

        if (description == null || description.length() == 0) {
            validationResult.addError("description", "description cannot be empty.");
        }

        return validationResult;
    }
}
