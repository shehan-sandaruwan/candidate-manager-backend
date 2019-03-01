package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.InterviewForm;
import lk.syscolabs.candidatemanager.model.Profile;
import lk.syscolabs.candidatemanager.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewFormRepository extends JpaRepository<InterviewForm, Integer> {
    public List<InterviewForm> findAllByScheduleByScheduleIdAndAndProfileByProfileId(Schedule scheduleByScheduleID, Profile profileByProfileId);
}