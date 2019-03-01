package lk.syscolabs.candidatemanager.validator;

import lk.syscolabs.candidatemanager.model.Feedback;
import lk.syscolabs.candidatemanager.model.Field;
import lk.syscolabs.candidatemanager.model.Schedule;
import lk.syscolabs.candidatemanager.service.FieldService;
import lk.syscolabs.candidatemanager.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackValidator extends Validator {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private FieldService fieldService;

    @Override
    public ValidationResult validate(Object o) {
        ValidationResult validationResult = new ValidationResult();
        Feedback feedback = (Feedback) o;

        String rating = feedback.getRating();
        Schedule scheduleByScheduleId = feedback.getScheduleByScheduleId();
        Field fieldByFieldId = feedback.getFieldByFieldId();

        if (rating == null || rating.length() == 0) {
            validationResult.addError("rating", "rating cannot be empty.");
        }

        if (scheduleByScheduleId == null) {
            validationResult.addError("scheduleByScheduleId", "scheduleByScheduleId cannot be empty.");
        } else if (scheduleService.getOne(scheduleByScheduleId.getId()) == null) {
            validationResult.addError("scheduleByScheduleId", "scheduleByScheduleId isnt available.");
        }

        if (fieldByFieldId == null) {
            validationResult.addError("fieldByFieldId", "fieldByFieldId cannot be empty.");
        } else if (fieldService.getOne(fieldByFieldId.getId()) == null) {
            validationResult.addError("fieldByFieldId", "fieldByFieldId isnt available.");
        }


        return validationResult;
    }
}
