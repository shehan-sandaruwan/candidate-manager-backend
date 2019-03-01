package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.Application;
import lk.syscolabs.candidatemanager.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Integer> {

    @Transactional
    @Modifying
    @Query("update State s set s.isActive = 0 where s.applicationByApplicationId.id = :appId")
    public void disableStates(@Param("appId") int applicationByApplicationId);

    public List<State> findAllByApplicationByApplicationId(Application applicationByApplicationId);

    public List<State> findAllByApplicationByApplicationIdAndIsActive(Application applicationByApplicationId, byte isActive);

    public List<State> findAllByStateNameAndIsActive(String stateName, byte isActive);
}