package lk.syscolabs.candidatemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Department {
    private int id;
    private String name;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "departmentByDepartmentId")
    public Collection<DepartmentState> getDepartmentStatesById() {
        return departmentStatesById;
    }

    public void setDepartmentStatesById(Collection<DepartmentState> departmentStatesById) {
        this.departmentStatesById = departmentStatesById;
    }
}
