package lk.syscolabs.candidatemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "old_application", schema = "cv_manager", catalog = "")
public class OldApplication {
    private int id;
    private String quarter;
    private Date cvRecievedDate;
    private String department;
    private String type;
    private String source;
    private String referedBy;
    private String candidateName;
    private String gender;
    private String contactNumber;
    private String email;
    private String institute;
    private String gpa;
    private String lastCompany;
    private String designitionApplied;
    private String dev;
    private String status;
    private String comments;
    private String firstTechIntPanel;
    private String firstTechIntDate;
    private String secondTechIntPanel;
    private String secondTechIntDate;
    private String thirdTechIntPanel;
    private String thirdTechIntDate;
    private String fourthTechIntPanel;
    private String fourthTechIntDate;
    private String fifthTechIntPanel;
    private String fifthTechIntDate;
    private String examDate;
    private String hrIntPanel;
    private String hrIntDate;
    private String candidateStatusEmail;
    private String internalRefralStatusEmail;

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
    @Column(name = "quarter")
    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    @Basic
    @Column(name = "cv_recieved_date")
    public Date getCvRecievedDate() {
        return cvRecievedDate;
    }

    public void setCvRecievedDate(Date cvRecievedDate) {
        this.cvRecievedDate = cvRecievedDate;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Column(name = "refered_by")
    public String getReferedBy() {
        return referedBy;
    }

    public void setReferedBy(String referedBy) {
        this.referedBy = referedBy;
    }

    @Basic
    @Column(name = "candidate_name")
    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
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
    @Column(name = "contact_number")
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Basic
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    @Column(name = "gpa")
    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
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
    @Column(name = "designition_applied")
    public String getDesignitionApplied() {
        return designitionApplied;
    }

    public void setDesignitionApplied(String designitionApplied) {
        this.designitionApplied = designitionApplied;
    }

    @Basic
    @Column(name = "dev")
    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "first_tech_int_panel")
    public String getFirstTechIntPanel() {
        return firstTechIntPanel;
    }

    public void setFirstTechIntPanel(String firstTechIntPanel) {
        this.firstTechIntPanel = firstTechIntPanel;
    }

    @Basic
    @Column(name = "first_tech_int_date")
    public String getFirstTechIntDate() {
        return firstTechIntDate;
    }

    public void setFirstTechIntDate(String firstTechIntDate) {
        this.firstTechIntDate = firstTechIntDate;
    }

    @Basic
    @Column(name = "second_tech_int_panel")
    public String getSecondTechIntPanel() {
        return secondTechIntPanel;
    }

    public void setSecondTechIntPanel(String secondTechIntPanel) {
        this.secondTechIntPanel = secondTechIntPanel;
    }

    @Basic
    @Column(name = "second_tech_int_date")
    public String getSecondTechIntDate() {
        return secondTechIntDate;
    }

    public void setSecondTechIntDate(String secondTechIntDate) {
        this.secondTechIntDate = secondTechIntDate;
    }

    @Basic
    @Column(name = "third_tech_int_panel")
    public String getThirdTechIntPanel() {
        return thirdTechIntPanel;
    }

    public void setThirdTechIntPanel(String thirdTechIntPanel) {
        this.thirdTechIntPanel = thirdTechIntPanel;
    }

    @Basic
    @Column(name = "third_tech_int_date")
    public String getThirdTechIntDate() {
        return thirdTechIntDate;
    }

    public void setThirdTechIntDate(String thirdTechIntDate) {
        this.thirdTechIntDate = thirdTechIntDate;
    }

    @Basic
    @Column(name = "fourth_tech_int_panel")
    public String getFourthTechIntPanel() {
        return fourthTechIntPanel;
    }

    public void setFourthTechIntPanel(String fourthTechIntPanel) {
        this.fourthTechIntPanel = fourthTechIntPanel;
    }

    @Basic
    @Column(name = "fourth_tech_int_date")
    public String getFourthTechIntDate() {
        return fourthTechIntDate;
    }

    public void setFourthTechIntDate(String fourthTechIntDate) {
        this.fourthTechIntDate = fourthTechIntDate;
    }

    @Basic
    @Column(name = "fifth_tech_int_panel")
    public String getFifthTechIntPanel() {
        return fifthTechIntPanel;
    }

    public void setFifthTechIntPanel(String fifthTechIntPanel) {
        this.fifthTechIntPanel = fifthTechIntPanel;
    }

    @Basic
    @Column(name = "fifth_tech_int_date")
    public String getFifthTechIntDate() {
        return fifthTechIntDate;
    }

    public void setFifthTechIntDate(String fifthTechIntDate) {
        this.fifthTechIntDate = fifthTechIntDate;
    }

    @Basic
    @Column(name = "exam_date")
    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    @Basic
    @Column(name = "hr_int_panel")
    public String getHrIntPanel() {
        return hrIntPanel;
    }

    public void setHrIntPanel(String hrIntPanel) {
        this.hrIntPanel = hrIntPanel;
    }

    @Basic
    @Column(name = "hr_int_date")
    public String getHrIntDate() {
        return hrIntDate;
    }

    public void setHrIntDate(String hrIntDate) {
        this.hrIntDate = hrIntDate;
    }

    @Basic
    @Column(name = "candidate_status_email")
    public String getCandidateStatusEmail() {
        return candidateStatusEmail;
    }

    public void setCandidateStatusEmail(String candidateStatusEmail) {
        this.candidateStatusEmail = candidateStatusEmail;
    }

    @Basic
    @Column(name = "internal_refral_status_email")
    public String getInternalRefralStatusEmail() {
        return internalRefralStatusEmail;
    }

    public void setInternalRefralStatusEmail(String internalRefralStatusEmail) {
        this.internalRefralStatusEmail = internalRefralStatusEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OldApplication that = (OldApplication) o;
        return id == that.id &&
                Objects.equals(quarter, that.quarter) &&
                Objects.equals(cvRecievedDate, that.cvRecievedDate) &&
                Objects.equals(department, that.department) &&
                Objects.equals(type, that.type) &&
                Objects.equals(source, that.source) &&
                Objects.equals(referedBy, that.referedBy) &&
                Objects.equals(candidateName, that.candidateName) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(contactNumber, that.contactNumber) &&
                Objects.equals(email, that.email) &&
                Objects.equals(institute, that.institute) &&
                Objects.equals(gpa, that.gpa) &&
                Objects.equals(lastCompany, that.lastCompany) &&
                Objects.equals(designitionApplied, that.designitionApplied) &&
                Objects.equals(dev, that.dev) &&
                Objects.equals(status, that.status) &&
                Objects.equals(comments, that.comments) &&
                Objects.equals(firstTechIntPanel, that.firstTechIntPanel) &&
                Objects.equals(firstTechIntDate, that.firstTechIntDate) &&
                Objects.equals(secondTechIntPanel, that.secondTechIntPanel) &&
                Objects.equals(secondTechIntDate, that.secondTechIntDate) &&
                Objects.equals(thirdTechIntPanel, that.thirdTechIntPanel) &&
                Objects.equals(thirdTechIntDate, that.thirdTechIntDate) &&
                Objects.equals(fourthTechIntPanel, that.fourthTechIntPanel) &&
                Objects.equals(fourthTechIntDate, that.fourthTechIntDate) &&
                Objects.equals(fifthTechIntPanel, that.fifthTechIntPanel) &&
                Objects.equals(fifthTechIntDate, that.fifthTechIntDate) &&
                Objects.equals(examDate, that.examDate) &&
                Objects.equals(hrIntPanel, that.hrIntPanel) &&
                Objects.equals(hrIntDate, that.hrIntDate) &&
                Objects.equals(candidateStatusEmail, that.candidateStatusEmail) &&
                Objects.equals(internalRefralStatusEmail, that.internalRefralStatusEmail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, quarter, cvRecievedDate, department, type, source, referedBy, candidateName, gender, contactNumber, email, institute, gpa, lastCompany, designitionApplied, dev, status, comments, firstTechIntPanel, firstTechIntDate, secondTechIntPanel, secondTechIntDate, thirdTechIntPanel, thirdTechIntDate, fourthTechIntPanel, fourthTechIntDate, fifthTechIntPanel, fifthTechIntDate, examDate, hrIntPanel, hrIntDate, candidateStatusEmail, internalRefralStatusEmail);
    }
}
