package lk.syscolabs.candidatemanager.service;

import lk.syscolabs.candidatemanager.model.Application;
import lk.syscolabs.candidatemanager.model.ApplicationDepartment;
import lk.syscolabs.candidatemanager.model.DepartmentState;
import lk.syscolabs.candidatemanager.model.State;
import lk.syscolabs.candidatemanager.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private DepartmentStateService departmentStateService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationDepartmentService applicationDepartmentService;

    public List<State> findAll() {
        return stateRepository.findAll();
    }

    public State getOne(Integer id) {
        if (stateRepository.existsById(id)) return stateRepository.getOne(id);
        else return null;
    }

    @Transactional
    public State save(State state) {
        State currentActiveState = getActiveState(state.getApplicationByApplicationId());
        stateRepository.disableStates(state.getApplicationByApplicationId().getId());
        Collection<DepartmentState> departmentStates = state.getDepartmentStatesById();
        State newState = stateRepository.save(state);
        if (state.getComment()!= null && state.getComment().equals("hr-shortlist-rollback")) {
            currentActiveState.getDepartmentStatesById().stream().forEach(departmentState -> {
                if (departmentState.getUserByAssignedTo().getId() == state.getUserByUserId().getId()) {
                    departmentState.setIsActive(Byte.parseByte("0"));
                    departmentStateService.save(departmentState);
                }
            });
        }

        else {

            if (state.getStateName().equals("hr-short-listed")) {
                departmentStates.stream().forEach(departmentState -> {
                    departmentState.setStateByStateId(newState);
                    departmentStateService.save(departmentState);
                });
            }
            if (state.getStateName().equals("line-rejected")) {
                currentActiveState.getDepartmentStatesById().stream().forEach(departmentState -> {
                    if (departmentState.getUserByAssignedTo().getId() == state.getUserByUserId().getId()) {
                        departmentState.setIsActive(Byte.parseByte("0"));
                        departmentStateService.save(departmentState);
                    }
                });

            }
            if (state.getStateName().equals("line-short-listed")) {
                ApplicationDepartment applicationDepartment = new ApplicationDepartment();
                currentActiveState.getDepartmentStatesById().stream().forEach(departmentState -> {
                    departmentState.setIsActive(Byte.parseByte("0"));
                    departmentStateService.save(departmentState);
                    if (departmentState.getUserByAssignedTo().getId() == state.getUserByUserId().getId()) {
                        applicationDepartment.setDepartmentByDepartmentId(departmentState.getDepartmentByDepartmentId());
                    }

                });

                applicationDepartment.setIsActive((byte) 1);
                applicationDepartment.setApplicationByApplicationId(state.getApplicationByApplicationId());
                applicationDepartmentService.save(applicationDepartment);
            }
        }

        return newState;
    }


    public List<State> findAllByApplicationByApplicationId(Application applicationByApplicationId) {
        List<State> states = stateRepository.findAllByApplicationByApplicationId(applicationByApplicationId);
        State submittedState = new State();
        submittedState.setStateName("submitted");
        submittedState.setApplicationByApplicationId(applicationByApplicationId);
        byte isActive = 0;
        if (states.isEmpty()) isActive = 1;
        submittedState.setIsActive(isActive);
        submittedState.setUpdatedTime(applicationByApplicationId.getCreatedTime());
        states.add(submittedState);
        try {
            Collections.sort(states, (s1, s2) -> s1.getUpdatedTime().compareTo(s2.getUpdatedTime()));
        } catch (Exception ex) {
            //noop
        }
        return states;
    }

    public State getActiveState(Application application) {
        List<State> states = findAllByApplicationByApplicationId(application);
        return states.stream().filter(state -> (state.getIsActive() == 1 || state.getIsActive()==2)).collect(Collectors.toList()).get(0);
    }

    public List<State> findAllByStateNameAndIsActive(String stateName, byte isActive) {
        return stateRepository.findAllByStateNameAndIsActive(stateName, isActive);
    }

}