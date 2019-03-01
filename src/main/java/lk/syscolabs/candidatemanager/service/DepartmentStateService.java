package lk.syscolabs.candidatemanager.service;

import lk.syscolabs.candidatemanager.model.Application;
import lk.syscolabs.candidatemanager.model.DepartmentState;
import lk.syscolabs.candidatemanager.model.State;
import lk.syscolabs.candidatemanager.model.User;
import lk.syscolabs.candidatemanager.repository.DepartmentStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class DepartmentStateService {
    @Autowired
    private DepartmentStateRepository departmentStateRepository;

    public List<DepartmentState> findAll() {
        return departmentStateRepository.findAll();
    }

    public DepartmentState getOne(Integer id) {

        if (departmentStateRepository.existsById(id)) return departmentStateRepository.getOne(id);
        else return null;
    }

    public DepartmentState save(DepartmentState departmentState) {
        return departmentStateRepository.saveAndFlush(departmentState);
    }

    public List<Application> findAllByAssignedTo(User assignedTo) {
        List<DepartmentState> departmentStates = departmentStateRepository.findAllByUserByAssignedToAndIsActive(assignedTo, Byte.parseByte("1"));
        List<Application> applications = new ArrayList<>();
        departmentStates.stream().forEach(departmentState -> {
            State state = departmentState.getStateByStateId();
            applications.add(state.getApplicationByApplicationId());
        });
        return new ArrayList<>(new HashSet<>(applications));
    }

}