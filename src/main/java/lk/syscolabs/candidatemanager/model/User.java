package lk.syscolabs.candidatemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private Collection<DepartmentState> departmentStatesById;
    private Collection<Panel> pannelsById;
    private Collection<State> statesById;
    private Collection<UserPrivilege> userPrivilegesById;
    private Collection<AdminPrivilege> adminPrivilegesById;

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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email, firstName, lastName);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "userByAssignedTo")
    public Collection<DepartmentState> getDepartmentStatesById() {
        return departmentStatesById;
    }

    public void setDepartmentStatesById(Collection<DepartmentState> departmentStatesById) {
        this.departmentStatesById = departmentStatesById;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "userByUserId")
    public Collection<Panel> getPannelsById() {
        return pannelsById;
    }

    public void setPannelsById(Collection<Panel> pannelsById) {
        this.pannelsById = pannelsById;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "userByUserId")
    public Collection<State> getStatesById() {
        return statesById;
    }

    public void setStatesById(Collection<State> statesById) {
        this.statesById = statesById;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "userByUserId")
    public Collection<UserPrivilege> getUserPrivilegesById() {
        return userPrivilegesById;
    }

    public void setUserPrivilegesById(Collection<UserPrivilege> userPrivilegeById) {
        this.userPrivilegesById = userPrivilegeById;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "userByUserId")
    public Collection<AdminPrivilege> getAdminPrivilegesById() {
        return adminPrivilegesById;
    }

    public void setAdminPrivilegesById(Collection<AdminPrivilege> adminPrivilegeById) {
        this.adminPrivilegesById = adminPrivilegeById;
    }
}
