package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    public List<Department> findAllByName(String name);
}