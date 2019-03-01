package lk.syscolabs.candidatemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "department_state", schema = "cv_manager")
public class DepartmentState {
    private int id;
    private byte isActive;
    private Department departmentByDepartmentId;
    private State stateByStateId;
    private User userByAssignedTo;

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
    @Column(name = "is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentState that = (DepartmentState) o;
        return id == that.id &&
                isActive == that.isActive;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, isActive);
    }

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    public Department getDepartmentByDepartmentId() {
        return departmentByDepartmentId;
    }

    public void setDepartmentByDepartmentId(Department departmentByDepartmentId) {
        this.departmentByDepartmentId = departmentByDepartmentId;
    }

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id", nullable = false)
    public State getStateByStateId() {
        return stateByStateId;
    }

    public void setStateByStateId(State stateByStateId) {
        this.stateByStateId = stateByStateId;
    }

    @ManyToOne
    @JoinColumn(name = "assigned_to", referencedColumnName = "id", nullable = false)
    public User getUserByAssignedTo() {
        return userByAssignedTo;
    }

    public void setUserByAssignedTo(User userByAssignedTo) {
        this.userByAssignedTo = userByAssignedTo;
    }
}
