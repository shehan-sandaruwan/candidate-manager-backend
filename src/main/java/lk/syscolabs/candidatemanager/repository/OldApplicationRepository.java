package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.OldApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OldApplicationRepository extends JpaRepository<OldApplication, Integer> {

    public List<OldApplication> findAllByCandidateNameContainingIgnoreCaseOrContactNumberContainingIgnoreCaseOrEmailEqualsIgnoreCase(
            String candidateName, String contactNumber, String email);

}