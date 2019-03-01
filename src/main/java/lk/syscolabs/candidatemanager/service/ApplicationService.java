package lk.syscolabs.candidatemanager.service;

import lk.syscolabs.candidatemanager.email.EmailMessage;
import lk.syscolabs.candidatemanager.email.EmailService;
import lk.syscolabs.candidatemanager.email.EmailServiceImpl;
import lk.syscolabs.candidatemanager.model.*;
import lk.syscolabs.candidatemanager.repository.ApplicationRepository;
import lk.syscolabs.candidatemanager.repository.OldApplicationRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private EmailMessage submission;
    @Autowired
    private OldApplicationRepository oldApplicationRepository;
    @Autowired
    private StateService stateService;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    private ApplicationDepartmentService applicationDepartmentService;

    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    public List<CommonApplication> match(String firstName, String lastName, String email, String contactNumber, String nic, Integer candidateId) {
        List<Application> newApplications = applicationRepository.
                findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailEqualsIgnoreCaseOrContactNumberContainingIgnoreCaseOrNicEqualsIgnoreCase
                        (firstName, lastName, email, contactNumber, nic);

        List<OldApplication> oldApplications = (oldApplicationRepository.findAllByCandidateNameContainingIgnoreCaseOrContactNumberContainingIgnoreCaseOrEmailEqualsIgnoreCase(
                firstName + " " + lastName, contactNumber, email
        ));

        List<CommonApplication> allMatches = new ArrayList<>();

        for (Application application : newApplications) {
            if (application.getId() != candidateId) {
                CommonApplication commonApplication = new CommonApplication();
                if (!application.getStatesById().isEmpty()) {
                    State lastState = application.getStatesById().stream().max(Comparator.comparing(State::getUpdatedTime)).get();
                    commonApplication.setStatus(lastState.getStateName());
                    commonApplication.setComment(lastState.getComment());
                } else {
                    commonApplication.setStatus("Submitted");
                    commonApplication.setComment("");
                }


                commonApplication.setName(application.getFirstName() + " " + application.getLastName());
                commonApplication.setEmail(application.getEmail());
                commonApplication.setPhone(application.getContactNumber());
                commonApplication.setNic(application.getNic());

                allMatches.add(commonApplication);
            }
        }

        for (OldApplication application : oldApplications) {
            CommonApplication commonApplication = new CommonApplication();
            commonApplication.setName(application.getCandidateName());
            commonApplication.setEmail(application.getEmail());
            commonApplication.setPhone(application.getContactNumber());
            commonApplication.setStatus(application.getStatus());
            commonApplication.setStatus(application.getComments());
        }


        return allMatches;
    }

    public Application getOne(Integer id) {

        if (applicationRepository.existsById(id)) return applicationRepository.getOne(id);
        else return null;
    }

    public Application save(Application application) {
        Application newApplication = applicationRepository.saveAndFlush(application);
//        emailService.sendSimpleMessage(application.getEmail(), "Application submission succeful", submission.getMessage(application));
        return newApplication;
    }

    public List<Application> findAllByCvName(String cvName) {
        return applicationRepository.findAllByCvName(cvName);
    }

    public List<ApplicationWithDepartment> findAllByStateName(String stateName) {
        List<Application> applications = applicationRepository.findAll();
        List<Application> applications1 = new ArrayList<>();
        List<ApplicationWithDepartment> applicationsWithDepartment = new ArrayList<>();
        if (stateName.equals("submitted")) {
            stateService.findAllByStateNameAndIsActive("pre-checked", (byte) 2).forEach(state -> applications1.add(state.getApplicationByApplicationId()));
            applications.stream().forEach(application -> {
                if (application.getStatesById().isEmpty()) applications1.add(application);
            });
        } else {
            stateService.findAllByStateNameAndIsActive(stateName, (byte) 1).forEach(state -> applications1.add(state.getApplicationByApplicationId()));
        }

        for (Application application:applications1){
            ApplicationWithDepartment applicationWithDepartment = new ApplicationWithDepartment();
            dozerBeanMapper.map(application,applicationWithDepartment);
            List<ApplicationDepartment> applicationDepartments = applicationDepartmentService.findAllByApplicationByApplicationId(application);
            if(applicationDepartments.isEmpty())applicationWithDepartment.setDepartment(null);
            else {
                applicationWithDepartment.setDepartment(applicationDepartments.get(0).getDepartmentByDepartmentId());
            }
            applicationsWithDepartment.add(applicationWithDepartment);

        }
        return applicationsWithDepartment;

    }

}