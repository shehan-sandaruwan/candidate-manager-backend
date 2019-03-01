package lk.syscolabs.candidatemanager.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import lk.syscolabs.candidatemanager.model.*;
import lk.syscolabs.candidatemanager.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private InterviewFormService interviewFormService;
    @Autowired
    private PanelService panelService;
    @Autowired
    private StateService stateService;
    @Autowired
    private UserService userService;
    @Autowired
    private FeedbackService feedbackService;

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public Schedule getOne(Integer id) {

        if (scheduleRepository.existsById(id)) return scheduleRepository.getOne(id);
        else return null;
    }

    @Transactional
    public List<Schedule> findAllByUserId(Integer id) {
        System.out.println("This is finding all shedules by user id");
        User user = userService.getOne(id);
        List<Panel> panels = panelService.findAllByUserByUserId(user);
        List<Schedule> schedules = new ArrayList<>();
        panels.forEach(panel -> {
            schedules.add(panel.getScheduleByScheduleId());
        });
        List<Schedule> activeSchedules = new ArrayList<>();
        List<Schedule> duplicateSchedules = new ArrayList<>();

        try{
            for (Schedule schedule : schedules) {
                if (stateService.getActiveState(schedule.getApplicationByApplicationId()).getStateName().equals("interview-scheduled") ) {
                    for(Schedule schedule1 : activeSchedules){
                        if(schedule1.getApplicationByApplicationId().getId() == schedule.getApplicationByApplicationId().getId()){
                            duplicateSchedules.add(schedule1);
                        }
                    }
                    activeSchedules.removeAll(duplicateSchedules);

                    activeSchedules.add(schedule);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return activeSchedules;
    }

    @Transactional
    public Schedule performInterview(Schedule newSchedule, String stateName, User user) {
        scheduleRepository.save(newSchedule);

        State state = new State();
        state.setApplicationByApplicationId(newSchedule.getApplicationByApplicationId());
        state.setStateName(stateName);
        state.setComment(newSchedule.getFinalRating());
        state.setIsActive((byte) 1);
        state.setUserByUserId(user);

        stateService.save(state);

        return newSchedule;

    }

    @Transactional
    public Schedule save(Schedule schedule, User user) {
        Schedule newSchedule = scheduleRepository.save(schedule);
        schedule.getInterviewFormsById().stream().forEach(interviewForm -> {
            interviewForm.setScheduleByScheduleId(newSchedule);
            interviewFormService.save(interviewForm);
        });
        schedule.getPannelsById().stream().forEach(panel -> {
            panel.setScheduleByScheduleId(newSchedule);
            panelService.save(panel);
        });
        State state = new State();
        state.setIsActive((byte) 1);
        state.setStateName("interview-scheduled");
        state.setApplicationByApplicationId(schedule.getApplicationByApplicationId());
        state.setUserByUserId(user);
        stateService.save(state);
        return newSchedule;
    }

    public List<Schedule> findAllPendingByApplicationByApplicationId(Application applicationByApplicationId){
        return scheduleRepository.findAllByApplicationByApplicationId(applicationByApplicationId).stream().filter(schedule -> !schedule.getTime().before(new Timestamp(System.currentTimeMillis()))).collect(Collectors.toList());
    }

    public List<Schedule> findAllInterviewedByApplicationId(Application applicationByApplicationId){
        List <Schedule> schedules = scheduleRepository.findAllByApplicationByApplicationId(applicationByApplicationId);

        return schedules;
    }

}