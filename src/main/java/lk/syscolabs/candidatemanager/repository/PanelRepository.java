package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.Panel;
import lk.syscolabs.candidatemanager.model.Schedule;
import lk.syscolabs.candidatemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PanelRepository extends JpaRepository<Panel, Integer> {

    public List<Panel> findAllByScheduleByScheduleIdAndUserByUserId(Schedule scheduleByScheduleId, User userByUserId);

    public List<Panel> findAllByUserByUserId(User userByUserId);

}