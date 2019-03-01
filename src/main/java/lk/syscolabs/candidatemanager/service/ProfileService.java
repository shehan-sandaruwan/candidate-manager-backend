package lk.syscolabs.candidatemanager.service;

import lk.syscolabs.candidatemanager.model.Field;
import lk.syscolabs.candidatemanager.model.Profile;
import lk.syscolabs.candidatemanager.model.ProfileField;
import lk.syscolabs.candidatemanager.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileFieldService profileFieldService;
    @Autowired
    private FieldService fieldService;

    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    public Profile getOne(Integer id) {

        if (profileRepository.existsById(id)) return profileRepository.getOne(id);
        else return null;
    }

    @Transactional
    public Profile save(Profile profile) {

        Profile newProfile = profileRepository.saveAndFlush(profile);
        Collection<ProfileField> profileFields = profile.getProfileFieldsById();
        for(ProfileField profileField : profileFields){
            profileField.setProfileByProfileId(newProfile);
            List<Field> fields = fieldService.findAllByName(profileField.getFieldByFieldId().getName());
            if(fields.isEmpty()){
                Field newField = fieldService.save(profileField.getFieldByFieldId());
                profileField.setFieldByFieldId(newField);
            }
            else{
                profileField.setFieldByFieldId(fields.get(0));
            }
            profileFieldService.save(profileField);
        }
        return newProfile;
    }

    public List<Profile> findAllByName(String name) {
        return profileRepository.findAllByName(name);
    }
}