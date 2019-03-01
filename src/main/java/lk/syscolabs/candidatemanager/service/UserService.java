package lk.syscolabs.candidatemanager.service;

import lk.syscolabs.candidatemanager.email.EmailMessage;
import lk.syscolabs.candidatemanager.email.EmailServiceImpl;
import lk.syscolabs.candidatemanager.model.Position;
import lk.syscolabs.candidatemanager.model.User;
import lk.syscolabs.candidatemanager.repository.UserRepository;
import lk.syscolabs.candidatemanager.util.AuthMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPrivilegeService userPrivilegeService;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private EmailMessage authenticate;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getOne(Integer id) {
        if (userRepository.existsById(id)) {
            return userRepository.getOne(id);

        } else return null;
    }

    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    public List<User> findAllByEmail(String email) {
        return userRepository.findAllByEmail(email);
    }

    public List<User> findAllByPositionByPositionIdAndCanShortListOrCanInterview(Position positionByPositionId, byte canShortList, byte canInterview) {
        List<User> users = new ArrayList<>();
        if (canInterview == 1)
            userPrivilegeService.findAllByPositionByPositionIdAndCanInterview(positionByPositionId, Byte.parseByte("1")).stream().forEach(userPrivilege -> users.add(userPrivilege.getUserByUserId()));
        else if (canShortList == 1)
            userPrivilegeService.findAllByPositionByPositionIdAndCanShortList(positionByPositionId, Byte.parseByte("1")).stream().forEach(userPrivilege -> users.add(userPrivilege.getUserByUserId()));
        return users;
    }

    public boolean hasShortListPrivilege(Integer userId, Position position) {
        List<User> users = findAllByPositionByPositionIdAndCanShortListOrCanInterview(position, (byte) 1, (byte) 0);
        return !users.stream().filter(user -> user.getId() == userId).collect(Collectors.toList()).isEmpty();
    }

    public boolean hasInterviewPrivilege(Integer userId, Position position) {
        List<User> users = findAllByPositionByPositionIdAndCanShortListOrCanInterview(position, (byte) 0, (byte) 1);
        return !users.stream().filter(user -> user.getId() == userId).collect(Collectors.toList()).isEmpty();
    }

    public void authenticate(AuthMessage authMessage) {
        emailService.sendSimpleMessage(authMessage.getEmail(), "candidate manager login", authenticate.getMessage(authMessage));
    }


}