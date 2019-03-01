package lk.syscolabs.candidatemanager.service;

import lk.syscolabs.candidatemanager.model.OldApplication;
import lk.syscolabs.candidatemanager.repository.OldApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OldApplicationService {
    @Autowired
    private OldApplicationRepository oldApplicationRepository;

    public List<OldApplication> findAll() {
        return oldApplicationRepository.findAll();
    }

    public OldApplication getOne(Integer id) {
        if (oldApplicationRepository.existsById(id)) return oldApplicationRepository.getOne(id);
        else return null;
    }

    public OldApplication save(OldApplication oldApplication) {
        return oldApplicationRepository.saveAndFlush(oldApplication);
    }

}