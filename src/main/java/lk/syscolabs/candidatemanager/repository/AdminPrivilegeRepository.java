package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.AdminPrivilege;
import lk.syscolabs.candidatemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminPrivilegeRepository extends JpaRepository<AdminPrivilege, Integer> {

    public List<AdminPrivilege> findAllByUserByUserId(User userByUserId);
}
