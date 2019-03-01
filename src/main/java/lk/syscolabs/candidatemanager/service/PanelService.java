package lk.syscolabs.candidatemanager.service;

import lk.syscolabs.candidatemanager.model.Panel;
import lk.syscolabs.candidatemanager.model.Schedule;
import lk.syscolabs.candidatemanager.model.User;
import lk.syscolabs.candidatemanager.repository.PanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanelService {
    @Autowired
    private PanelRepository panelRepository;

    public List<Panel> findAll() {
        return panelRepository.findAll();
    }

    public Panel getOne(Integer id) {
        if (panelRepository.existsById(id)) return panelRepository.getOne(id);
        else return null;
    }

    public Panel save(Panel panel) {
        return panelRepository.saveAndFlush(panel);
    }

    public List<Panel> findAllByScheduleByScheduleIdAndUserByUserId(Schedule scheduleByScheduleId, User userByUserId) {
        return panelRepository.findAllByScheduleByScheduleIdAndUserByUserId(scheduleByScheduleId, userByUserId);
    }

    public List<Panel> findAllByUserByUserId(User userByUserId) {
        return panelRepository.findAllByUserByUserId(userByUserId);
    }

}