package lk.syscolabs.candidatemanager.validator;

import lk.syscolabs.candidatemanager.model.Application;
import lk.syscolabs.candidatemanager.model.InterviewForm;
import lk.syscolabs.candidatemanager.model.Panel;
import lk.syscolabs.candidatemanager.model.Schedule;
import lk.syscolabs.candidatemanager.service.ApplicationService;
import lk.syscolabs.candidatemanager.service.ProfileService;
import lk.syscolabs.candidatemanager.service.StateService;
import lk.syscolabs.candidatemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class ScheduleValidator extends Validator {

    private static final Set<String> venues = new HashSet<>(Arrays.asList(
            "DM-0-ALBA", "DM-0-CERN", "DM-1-ARGONNE", "DM-2-AMES", "DM-3-BROOKHAVEN", "DM-5-FERMILABS", "DM-6-SIMULA", "DM-4-AUDITORIUM", "IBC-5-CAVENDISH", "IBC-5-BELL", "IBC-5-LIGO", "IBC-7-BERKERLY", "IBC-7-DARPA", "IBC-8-SANDIA"));
    private static final Set<String> types = new HashSet<>(Arrays.asList(
            "tech-1", "tech-2", "tech-3", "hr", "md","gm", "mgt", "other"));
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private UserService userService;
    @Autowired
    private StateService stateService;
    @Autowired
    private ProfileService profileService;

    @Override
    public ValidationResult validate(Object o) {

        ValidationResult validationResult = new ValidationResult();
        Schedule schedule = (Schedule) o;

        String venue = schedule.getVenue();
        String type = schedule.getType();
        Timestamp time = schedule.getTime();
        String description = schedule.getDescription();
        String finalRating = schedule.getFinalRating();
        Application applicationByApplicationId = schedule.getApplicationByApplicationId();
        Collection<Panel> panels = schedule.getPannelsById();
        Collection<InterviewForm> interviewForms = schedule.getInterviewFormsById();
        Application applicant = applicationService.getOne(applicationByApplicationId.getId());

        if (venue == null || venue.length() == 0) {
            validationResult.addError(ValidatorFields.VENUE, ValidatorFields.EMPTY);
        } else if (!venues.contains(venue)) {
            validationResult.addError(ValidatorFields.VENUE, ValidatorFields.UNAVAILABLE);
        }

        if (type== null || type.length() == 0) {
            validationResult.addError(ValidatorFields.TYPE, ValidatorFields.EMPTY);
        } else if (!types.contains(type)) {
            validationResult.addError(ValidatorFields.TYPE, ValidatorFields.UNAVAILABLE);
        }

        if (time == null) {
            validationResult.addError(ValidatorFields.TIME, ValidatorFields.EMPTY);
        } else if (time.before(new Timestamp(System.currentTimeMillis()))) {
            validationResult.addError(ValidatorFields.TIME, ValidatorFields.PAST_TIME);
        }

        if (description == null || description.length() == 0) {
            validationResult.addError(ValidatorFields.DESCRIPTION, ValidatorFields.EMPTY);
        }

        if (finalRating == null || finalRating.length() == 0) {
            validationResult.addError(ValidatorFields.FINAL_RATING, ValidatorFields.EMPTY);
        }

        if (applicationByApplicationId == null) {
            validationResult.addError(ValidatorFields.APPLICATION_BY_APPLICATION_ID, ValidatorFields.EMPTY);
        } else if (applicant == null) {
            validationResult.addError(ValidatorFields.APPLICATION_BY_APPLICATION_ID, ValidatorFields.UNAVAILABLE);
        } else if (!stateService.getActiveState(applicant).getStateName().equals("line-short-listed")&&!stateService.getActiveState(applicant).getStateName().equals("interviewed")) {
            validationResult.addError(ValidatorFields.APPLICATION_BY_APPLICATION_ID, ValidatorFields.NOT_LINE_SHORT_LISTED);
        }

        if (isEmptyList(panels)) {
            validationResult.addError(ValidatorFields.PANEL_BY_PANEL_ID, ValidatorFields.EMPTY);
        } else {
            int i = 0;
            for (Panel panel : panels) {
                if (userService.getOne(panel.getUserByUserId().getId()) == null) {
                    validationResult.addError("pannel[" + i + "]", ValidatorFields.USER_BY_USER_ID + " " + ValidatorFields.UNAVAILABLE);
                } else if (applicant != null && !userService.hasInterviewPrivilege(panel.getUserByUserId().getId(), applicant.getPositionByPositionId())) {
                    validationResult.addError("pannel[" + i + "]", ValidatorFields.NOT_PRIVILEGED);
                }
                i++;
            }
        }

        if (isEmptyList(interviewForms)) {
            validationResult.addError(ValidatorFields.INTERVIEW_FORM_BY_INTERVIEW_FORM_ID, ValidatorFields.EMPTY);
        } else {
            int i = 0;
            for (InterviewForm interviewForm : interviewForms) {
                if (profileService.getOne(interviewForm.getProfileByProfileId().getId()) == null) {
                    validationResult.addError("interviewFormsById[" + i + "]", ValidatorFields.PROFILE_BY_PROFILE_ID + " " + ValidatorFields.UNAVAILABLE);
                }
                i++;
            }
        }

        return validationResult;
    }
}
