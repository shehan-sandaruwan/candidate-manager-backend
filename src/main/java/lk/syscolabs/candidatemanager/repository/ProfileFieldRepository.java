package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.Field;
import lk.syscolabs.candidatemanager.model.Profile;
import lk.syscolabs.candidatemanager.model.ProfileField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileFieldRepository extends JpaRepository<ProfileField, Integer> {

    public List<ProfileField> findAllByFieldByFieldIdAndProfileByProfileId(Field fieldByFieldId, Profile profileByProfileId);
}