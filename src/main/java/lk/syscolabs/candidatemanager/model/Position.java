package lk.syscolabs.candidatemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Position {
    private int id;
    private String name;
    private String description;
    private String created_time;
    private String department;
    private int  nopositions;
    private int isOpen;
    private Collection<Application> applicationsById;
    private Collection<UserPrivilege> userPrivilegesById;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    @Basic
    @Column(name = "is_open")
    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }
//
    @Basic
    @Column(name = "created_time")
    public String getCreated_time() { return created_time; }

    public void setCreated_time(String created_time) { this.created_time = created_time;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return id == position.id &&
                isOpen == position.isOpen &&
                Objects.equals(name, position.name) &&
                Objects.equals(description, position.description)&&
                Objects.equals(department, position.department)
                &&
                Objects.equals(created_time, position.created_time)
                ;

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, department, isOpen, created_time);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "positionByPositionId")
    public Collection<Application> getApplicationsById() {
        return applicationsById;
    }

    public void setApplicationsById(Collection<Application> applicationsById) {
        this.applicationsById = applicationsById;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "positionByPositionId")
    public Collection<UserPrivilege> getUserPrivilegesById() {
        return userPrivilegesById;
    }

    public void setUserPrivilegesById(Collection<UserPrivilege> userPrivilegeById) {
        this.userPrivilegesById = userPrivilegeById;
    }
}
