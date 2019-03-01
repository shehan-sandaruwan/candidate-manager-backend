package lk.syscolabs.candidatemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "application_department", schema = "cv_manager")
public class ApplicationDepartment {

    private int id;
    private Application applicationByApplicationId;
    private Department departmentByDepartmentId;
    private byte isActive;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    public Department getDepartmentByDepartmentId() {
        return departmentByDepartmentId;
    }

    public void setDepartmentByDepartmentId(Department departmentBydepartmentId) {
        this.departmentByDepartmentId = departmentBydepartmentId;
    }

    @Basic
    @Column(name = "is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationDepartment)) return false;
        ApplicationDepartment that = (ApplicationDepartment) o;
        return getId() == that.getId() &&
                getIsActive() == that.getIsActive() &&
                Objects.equals(getApplicationByApplicationId(), that.getApplicationByApplicationId()) &&
                Objects.equals(getDepartmentByDepartmentId(), that.getDepartmentByDepartmentId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getApplicationByApplicationId(), getDepartmentByDepartmentId(), getIsActive());
    }
}
