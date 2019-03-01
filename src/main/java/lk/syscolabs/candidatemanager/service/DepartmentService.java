package lk.syscolabs.candidatemanager.service;

import lk.syscolabs.candidatemanager.model.Department;
import lk.syscolabs.candidatemanager.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department getOne(Integer id) {

        if (departmentRepository.existsById(id)) return departmentRepository.getOne(id);
        else return null;
    }

    public Department save(Department department) {
        return departmentRepository.saveAndFlush(department);
    }

    public List<Department> findAllByName(String name) {
        return departmentRepository.findAllByName(name);
    }

}