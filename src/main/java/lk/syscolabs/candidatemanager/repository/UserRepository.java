package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    public List<User> findAllByEmail(String email);
}