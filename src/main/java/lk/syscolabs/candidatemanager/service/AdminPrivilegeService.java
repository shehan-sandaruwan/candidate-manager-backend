package lk.syscolabs.candidatemanager.service;

import lk.syscolabs.candidatemanager.model.AdminPrivilege;
import lk.syscolabs.candidatemanager.model.User;
import lk.syscolabs.candidatemanager.repository.AdminPrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPrivilegeService {
    @Autowired
    private AdminPrivilegeRepository adminPrivilegeRepository;

    public AdminPrivilege getOne(Integer id) {
        if (adminPrivilegeRepository.existsById(id)) return adminPrivilegeRepository.getOne(id);
        else return null;
    }

    public List<AdminPrivilege> findAll() {
        return adminPrivilegeRepository.findAll();
    }

    public AdminPrivilege save(AdminPrivilege adminPrivilege) {
        return adminPrivilegeRepository.save(adminPrivilege);
    }

    public List<AdminPrivilege> findAllByUserByUserId(User userByUserId) {
        return adminPrivilegeRepository.findAllByUserByUserId(userByUserId);
    }
}
