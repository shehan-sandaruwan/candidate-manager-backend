package lk.syscolabs.candidatemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Application {
    private int id;
    private String firstName;
    private String lastName;
    private String nic;
    private String institute;
    private String source;
    private String gender;
    private String lastCompany;
    private String email;
    private String contactNumber;
    private String cvName;
//    private String currentPosition;
//    private String experience;
    private Timestamp createdTime = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());;
    private Position positionByPositionId;
    private Collection<Schedule> schedulesById;
    private Collection<State> statesById;


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

    @Basic
    @Column(name = "nic")
    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Basic
    @Column(name = "institute")
    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    @Basic
    @Column(name = "source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "last_company")
    public String getLastCompany() {
        return lastCompany;
    }

    public void setLastCompany(String lastCompany) {
        this.lastCompany = lastCompany;
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
    @Column(name = "contact_number")
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Basic
    @Column(name = "cv_name")
    public String getCvName() {
        return cvName;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName;
    }

    @Basic
    @Column(name = "created_time")
    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }
//
//    @Basic
//    @Column(name = "current_pos")
//    public String getCurrentPosition(){
//        return currentPosition;
//    }
//
//    public void setCurrentPosition(String currentPosition) {
//        this.currentPosition = currentPosition;
//    }
//
//    @Basic
//    @Column(name = "experience")
//    public String getExperience() {
//        return experience;
//    }
//
//    public void setExperience(String experience) {
//        this.experience = experience;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Application that = (Application) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName)
                && Objects.equals(nic, that.nic) && Objects.equals(institute, that.institute)
                && Objects.equals(source, that.source) && Objects.equals(gender, that.gender)
                && Objects.equals(lastCompany, that.lastCompany) && Objects.equals(email, that.email)
                && Objects.equals(contactNumber, that.contactNumber) && Objects.equals(cvName, that.cvName)
                && Objects.equals(createdTime, that.createdTime);

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, nic, institute, source, gender, lastCompany, email, contactNumber,
                cvName, createdTime);
    }

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id", nullable = false)
    public Position getPositionByPositionId() {
        return positionByPositionId;
    }

    public void setPositionByPositionId(Position positionByPositionId) {
        this.positionByPositionId = positionByPositionId;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "applicationByApplicationId")
    public Collection<Schedule> getSchedulesById() {
        return schedulesById;
    }

    public void setSchedulesById(Collection<Schedule> schedulesById) {
        this.schedulesById = schedulesById;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "applicationByApplicationId")
    public Collection<State> getStatesById() {
        return statesById;
    }

    public void setStatesById(Collection<State> statesById) {
        this.statesById = statesById;
    }


}
