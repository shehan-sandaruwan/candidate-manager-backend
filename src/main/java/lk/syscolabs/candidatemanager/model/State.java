package lk.syscolabs.candidatemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class State {
    private int id;
    private String stateName;
    private String comment;
    private byte isActive;
    private Application applicationByApplicationId;
    private User userByUserId;
    private Timestamp updatedTime =new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<DepartmentState> departmentStatesById;

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
    @Column(name = "state_name")
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "updated_time")
    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        State state = (State) o;
        return id == state.id && isActive == state.isActive && Objects.equals(stateName, state.stateName)
                && Objects.equals(comment, state.comment) && Objects.equals(updatedTime, state.updatedTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, stateName, comment, isActive, updatedTime);
    }


    @OneToMany(mappedBy = "stateByStateId")
    @JsonIgnore
    public Collection<DepartmentState> getDepartmentStatesById() {
        return departmentStatesById;
    }

    public void setDepartmentStatesById(Collection<DepartmentState> departmentStatesById) {
        this.departmentStatesById = departmentStatesById;
    }

    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id", nullable = false)
    public Application getApplicationByApplicationId() {
        return applicationByApplicationId;
    }

    public void setApplicationByApplicationId(Application applicationByApplicationId) {
        this.applicationByApplicationId = applicationByApplicationId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
