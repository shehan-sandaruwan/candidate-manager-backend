package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.Application;
import lk.syscolabs.candidatemanager.model.ApplicationDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ApplicationDepartmentRepository extends JpaRepository<ApplicationDepartment,Integer> {

    public List<ApplicationDepartment> findAllByApplicationByApplicationId(Application applicationByApplicationID);

    @Transactional
    @Modifying
    @Query(value = "update ApplicationDepartment a set a.isActive = 0 where a.applicationByApplicationId.id = :appId")
    public void disableDepartments(@Param("appId") int applicationByApplicationId);
}
