package lk.syscolabs.candidatemanager.repository;

import lk.syscolabs.candidatemanager.model.Position;
import lk.syscolabs.candidatemanager.model.User;
import lk.syscolabs.candidatemanager.model.UserPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPrivilegeRepository extends JpaRepository<UserPrivilege, Integer> {

    public List<UserPrivilege> findAllByUserByUserIdAndPositionByPositionId(User userByUserId, Position positionByPositionId);

    public List<UserPrivilege> findAllByPositionByPositionIdAndCanShortList(Position positionByPositionId, byte canShortList);

    public List<UserPrivilege> findAllByPositionByPositionIdAndCanInterview(Position positionByPositionId, byte canInterview);

    public List<UserPrivilege> findAllByUserByUserId(User userByUserId);
}
