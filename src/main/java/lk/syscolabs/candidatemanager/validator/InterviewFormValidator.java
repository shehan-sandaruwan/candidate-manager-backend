package lk.syscolabs.candidatemanager.validator;

import lk.syscolabs.candidatemanager.model.InterviewForm;
import lk.syscolabs.candidatemanager.model.Profile;
import lk.syscolabs.candidatemanager.model.Schedule;
import lk.syscolabs.candidatemanager.service.InterviewFormService;
import lk.syscolabs.candidatemanager.service.ProfileFieldService;
import lk.syscolabs.candidatemanager.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterviewFormValidator extends Validator {

    @Autowired
    private InterviewFormService interviewFormService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ProfileFieldService profileFieldService;

    @Override
    public ValidationResult validate(Object o) {
        ValidationResult validationResult = new ValidationResult();
        InterviewForm interviewForm = (InterviewForm) o;

        Schedule scheduleByScheduleId = interviewForm.getScheduleByScheduleId();
        Profile profileByProfileId = interviewForm.getProfileByProfileId();

        if (scheduleByScheduleId == null) {
            validationResult.addError("scheduleByScheduleId", "scheduleByScheduleId cannot be empty.");
        } else if (scheduleService.getOne(scheduleByScheduleId.getId()) == null) {
            validationResult.addError("scheduleByScheduleId", "scheduleByScheduleId isnt available.");
        }

        if (profileByProfileId == null) {
            validationResult.addError("profileByProfileId", "profileByProfileId cannot be empty.");
        } else if (profileFieldService.getOne(profileByProfileId.getId()) == null) {
            validationResult.addError("profileByProfileId", "profileByProfileId isnt available.");
        } else if (!interviewFormService.findAllByScheduleByScheduleIdAndAndProfileByProfileId(scheduleByScheduleId, profileByProfileId).isEmpty()) {
            validationResult.addError("combination", "combination cannot be duplicated.");
        }

        return validationResult;
    }
}
