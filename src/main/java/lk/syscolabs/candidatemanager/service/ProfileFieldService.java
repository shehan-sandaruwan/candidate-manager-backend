package lk.syscolabs.candidatemanager.service;

import lk.syscolabs.candidatemanager.model.Field;
import lk.syscolabs.candidatemanager.model.Profile;
import lk.syscolabs.candidatemanager.model.ProfileField;
import lk.syscolabs.candidatemanager.repository.ProfileFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileFieldService {
    @Autowired
    private ProfileFieldRepository profileFieldRepository;

    public List<ProfileField> findAll() {
        return profileFieldRepository.findAll();
    }

    public ProfileField getOne(Integer id) {

        if (profileFieldRepository.existsById(id)) return profileFieldRepository.getOne(id);
        else return null;
    }

    public ProfileField save(ProfileField profileField) {
        return profileFieldRepository.saveAndFlush(profileField);
    }

    public List<ProfileField> findAllByFieldByFieldIdAndProfileByProfileId(Field fieldByFieldId, Profile profileByProfileId) {
        return profileFieldRepository.findAllByFieldByFieldIdAndProfileByProfileId(fieldByFieldId, profileByProfileId);
    }

}