package lk.syscolabs.candidatemanager.service;

import lk.syscolabs.candidatemanager.model.Position;
import lk.syscolabs.candidatemanager.model.User;
import lk.syscolabs.candidatemanager.model.UserPrivilege;
import lk.syscolabs.candidatemanager.repository.UserPrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPrivilegeService {
    @Autowired
    private UserPrivilegeRepository userPrivilegeRepository;

    public List<UserPrivilege> findAll() {
        return userPrivilegeRepository.findAll();
    }

    public UserPrivilege getOne(Integer id) {

        if (userPrivilegeRepository.existsById(id)) return userPrivilegeRepository.getOne(id);
        else return null;
    }


    public UserPrivilege save(UserPrivilege userPrivilege) {
        return userPrivilegeRepository.saveAndFlush(userPrivilege);
    }

    public List<UserPrivilege> findAllByUserByUserIdAndPositionByPositionId(User userByUserId, Position positionByPositionId) {
        return userPrivilegeRepository.findAllByUserByUserIdAndPositionByPositionId(userByUserId, positionByPositionId);
    }

    public List<UserPrivilege> findAllByPositionByPositionIdAndCanShortList(Position positionByPositionId, byte canShortList) {
        return userPrivilegeRepository.findAllByPositionByPositionIdAndCanShortList(positionByPositionId, canShortList);
    }

    public List<UserPrivilege> findAllByPositionByPositionIdAndCanInterview(Position positionByPositionId, byte canInterview) {
        return userPrivilegeRepository.findAllByPositionByPositionIdAndCanInterview(positionByPositionId, canInterview);
    }

    public List<UserPrivilege> findAllByUserByUserId(User userByUserId) {
        return userPrivilegeRepository.findAllByUserByUserId(userByUserId);
    }
}