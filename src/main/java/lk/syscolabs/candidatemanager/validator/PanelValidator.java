package lk.syscolabs.candidatemanager.validator;

import lk.syscolabs.candidatemanager.model.Panel;
import lk.syscolabs.candidatemanager.model.Schedule;
import lk.syscolabs.candidatemanager.model.User;
import lk.syscolabs.candidatemanager.service.PanelService;
import lk.syscolabs.candidatemanager.service.ScheduleService;
import lk.syscolabs.candidatemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PanelValidator extends Validator {

    @Autowired
    private PanelService panelService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private UserService userService;

    @Override
    public ValidationResult validate(Object o) {
        ValidationResult validationResult = new ValidationResult();
        Panel panel = (Panel) o;

        Schedule scheduleByScheduleId = panel.getScheduleByScheduleId();
        User userByUserId = panel.getUserByUserId();

        if (scheduleByScheduleId == null) {
            validationResult.addError("scheduleByScheduleId", "scheduleByScheduleId cannot  be empty.");
        } else if (scheduleService.getOne(scheduleByScheduleId.getId()) == null) {
            validationResult.addError("scheduleByScheduleId", "scheduleByScheduleId isnt available.");
        }

        if (userByUserId == null) {
            validationResult.addError("userByUserId", "userByUserId cannot be empty.");
        } else if (userService.getOne(userByUserId.getId()) == null) {
            validationResult.addError("userByUserId", "userByUserId isnt available.");
        }

        if (!panelService.findAllByScheduleByScheduleIdAndUserByUserId(scheduleByScheduleId, userByUserId).isEmpty()) {
            validationResult.addError("combination", "combination cannot be duplicated.");
        }

        return validationResult;
    }
}
