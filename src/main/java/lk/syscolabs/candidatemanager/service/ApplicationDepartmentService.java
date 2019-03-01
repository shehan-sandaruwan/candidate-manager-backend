package lk.syscolabs.candidatemanager.service;

import lk.syscolabs.candidatemanager.model.Application;
import lk.syscolabs.candidatemanager.model.ApplicationDepartment;
import lk.syscolabs.candidatemanager.repository.ApplicationDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicationDepartmentService {
    @Autowired
    private ApplicationDepartmentRepository applicationDepartmentRepository;

    public ApplicationDepartment getOne(Integer id){
        if(applicationDepartmentRepository.existsById(id)){
            return applicationDepartmentRepository.getOne(id);
        }
        return null;
    }

    public List<ApplicationDepartment> findAllByApplicationByApplicationId(Application applicationByApplicationID){
        return applicationDepartmentRepository.findAllByApplicationByApplicationId(applicationByApplicationID);
    }

    @Transactional
    public ApplicationDepartment save(ApplicationDepartment applicationDepartment){
        applicationDepartmentRepository.disableDepartments(applicationDepartment.getApplicationByApplicationId().getId());
        return applicationDepartmentRepository.save(applicationDepartment);
    }

}
