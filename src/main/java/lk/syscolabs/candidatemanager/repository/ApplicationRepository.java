package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    public List<Application> findAllByCvName(String cvName);

    public List<Application> findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailEqualsIgnoreCaseOrContactNumberContainingIgnoreCaseOrNicEqualsIgnoreCase(String firstName, String lastName, String email, String contactNumber, String nic);
}