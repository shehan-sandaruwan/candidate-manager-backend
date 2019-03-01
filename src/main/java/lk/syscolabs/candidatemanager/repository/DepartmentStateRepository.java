package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.DepartmentState;
import lk.syscolabs.candidatemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentStateRepository extends JpaRepository<DepartmentState, Integer> {

    public List<DepartmentState> findAllByUserByAssignedToAndIsActive(User userByAssignedTo, byte isActive);

}