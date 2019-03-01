package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.Feedback;
import lk.syscolabs.candidatemanager.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    public List<Feedback> findAllByScheduleByScheduleId(Schedule scheduleByScheduleId);
}