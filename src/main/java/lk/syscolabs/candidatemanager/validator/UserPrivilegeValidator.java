package lk.syscolabs.candidatemanager.validator;

import lk.syscolabs.candidatemanager.model.Position;
import lk.syscolabs.candidatemanager.model.User;
import lk.syscolabs.candidatemanager.model.UserPrivilege;
import lk.syscolabs.candidatemanager.service.PositionService;
import lk.syscolabs.candidatemanager.service.UserPrivilegeService;
import lk.syscolabs.candidatemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPrivilegeValidator extends Validator {

    @Autowired
    private UserPrivilegeService userPrivilegeService;
    @Autowired
    private UserService userService;
    @Autowired
    private PositionService positionService;

    @Override
    public ValidationResult validate(Object o) {
        ValidationResult validationResult = new ValidationResult();
        UserPrivilege userPrivilege = (UserPrivilege) o;

        byte canInterview = userPrivilege.getCanInterview();
        byte canShortList = userPrivilege.getCanShortList();
        User userByUserId = userPrivilege.getUserByUserId();
        Position positionByPositionId = userPrivilege.getPositionByPositionId();

        if (canInterview != 0 && canInterview != 1) {
            validationResult.addError("canInterview", "canInterview should be either 0 or 1.");
        }
        if (canShortList != 0 && canShortList != 1) {
            validationResult.addError("canShortList", "canShortList should be either 0 or 1.");
        }

        if (userByUserId == null) {
            validationResult.addError("userByUserId", "userByUserId cannot be empty.");
        } else if (userService.getOne(userByUserId.getId()) == null) {
            validationResult.addError("userByUserId", "userByUserId isnt available.");
        }

        if (positionByPositionId == null) {
            validationResult.addError("positionByPositionId", "positionByPositionId cannot be empty.");
        } else if (positionService.getOne(positionByPositionId.getId()) == null) {
            validationResult.addError("positionByPositionId", "positionByPositionId isnt available.");
        }

        if (!userPrivilegeService.findAllByUserByUserIdAndPositionByPositionId(userByUserId, positionByPositionId).isEmpty() && userPrivilegeService.findAllByUserByUserIdAndPositionByPositionId(userByUserId, positionByPositionId).get(0).getId() != userPrivilege.getId()) {
            validationResult.addError("combination", "combination cannot be duplicated.");
        }

        return validationResult;
    }
}
