package lk.syscolabs.candidatemanager.service;

import lk.syscolabs.candidatemanager.model.Feedback;
import lk.syscolabs.candidatemanager.model.Schedule;
import lk.syscolabs.candidatemanager.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    public Feedback getOne(Integer id) {

        if (feedbackRepository.existsById(id)) return feedbackRepository.getOne(id);
        else return null;
    }

    public Feedback save(Feedback feedback) {
        return feedbackRepository.saveAndFlush(feedback);
    }

    public List<Feedback> save(List<Feedback> feedbacks){return feedbackRepository.saveAll(feedbacks);}

    public List<Feedback> findAllByScheduleByScheduleId(Schedule scheduleByScheduleId){
        return feedbackRepository.findAllByScheduleByScheduleId(scheduleByScheduleId);
    }

}