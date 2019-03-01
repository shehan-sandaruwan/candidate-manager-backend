package lk.syscolabs.candidatemanager.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "admin_privilege", schema = "cv_manager")
public class AdminPrivilege {
    private int id;
    private Byte isAdmin;
    private Byte isSuperAdmin;
    private User userByUserId;

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
    @Column(name = "is_admin")
    public Byte getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Byte isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Basic
    @Column(name = "is_super_admin")
    public Byte getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(Byte isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminPrivilege that = (AdminPrivilege) o;
        return id == that.id &&
                Objects.equals(isAdmin, that.isAdmin) &&
                Objects.equals(isSuperAdmin, that.isSuperAdmin) &&
                Objects.equals(userByUserId, that.userByUserId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, isAdmin, isSuperAdmin, userByUserId);
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
