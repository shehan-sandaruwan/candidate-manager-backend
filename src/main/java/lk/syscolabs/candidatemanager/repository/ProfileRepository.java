package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    public List<Profile> findAllByName(String name);
}