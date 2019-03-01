package lk.syscolabs.candidatemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user_privilege", schema = "cv_manager", catalog = "")
public class UserPrivilege {
    private int id;
    private byte canInterview;
    private byte canShortList;
    private User userByUserId;
    private Position positionByPositionId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "can_interview")
    public byte getCanInterview() {
        return canInterview;
    }

    public void setCanInterview(byte canInterview) {
        this.canInterview = canInterview;
    }

    @Basic
    @Column(name = "can_short_list")
    public byte getCanShortList() {
        return canShortList;
    }

    public void setCanShortList(byte canShortList) {
        this.canShortList = canShortList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrivilege that = (UserPrivilege) o;
        return id == that.id &&
                canInterview == that.canInterview &&
                canShortList == that.canShortList;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, canInterview, canShortList);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id", nullable = false)
    public Position getPositionByPositionId() {
        return positionByPositionId;
    }

    public void setPositionByPositionId(Position positionByPositionId) {
        this.positionByPositionId = positionByPositionId;
    }
}
