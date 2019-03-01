package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.Application;
import lk.syscolabs.candidatemanager.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    public List<Schedule> findAllByApplicationByApplicationId(Application applicationByApplicationId);

}