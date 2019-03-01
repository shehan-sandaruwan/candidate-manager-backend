package lk.syscolabs.candidatemanager.service;

import lk.syscolabs.candidatemanager.model.InterviewForm;
import lk.syscolabs.candidatemanager.model.Profile;
import lk.syscolabs.candidatemanager.model.Schedule;
import lk.syscolabs.candidatemanager.repository.InterviewFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewFormService {
    @Autowired
    private InterviewFormRepository interviewFormRepository;

    public List<InterviewForm> findAll() {
        return interviewFormRepository.findAll();
    }

    public InterviewForm getOne(Integer id) {
        if (interviewFormRepository.existsById(id)) return interviewFormRepository.getOne(id);
        else return null;
    }

    public InterviewForm save(InterviewForm interviewForm) {
        return interviewFormRepository.saveAndFlush(interviewForm);
    }

    public List<InterviewForm> findAllByScheduleByScheduleIdAndAndProfileByProfileId(Schedule scheduleByScheduleID, Profile profileByProfileId) {
        return interviewFormRepository.findAllByScheduleByScheduleIdAndAndProfileByProfileId(scheduleByScheduleID, profileByProfileId);
    }

}