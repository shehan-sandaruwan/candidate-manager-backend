package lk.syscolabs.candidatemanager.email;

import lk.syscolabs.candidatemanager.model.Application;
import lk.syscolabs.candidatemanager.util.TemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;

public class Submission implements EmailMessage {
    @Autowired
    private TemplateLoader templateLoader;

    @Override
    public String getMessage(Object o) {
        String template = templateLoader.getTemplate("submission");
        Application application = (Application) o;
        String data = "<table style=\"width: 336px;\">\n" + "<tbody>" + getRow("Name", application.getFirstName() + " " + application.getLastName())
                + getRow("NIC", application.getNic()) + getRow("EMail", application.getEmail())
                + getRow("Gender", application.getGender()) + getRow("Institute", application.getInstitute())
                + getRow("Contact Number", application.getContactNumber()) + "</tbody>\n" + "</table>\n";
        return template.replace("@salutation@", application.getFirstName())
                .replace("@description@", "Your application was successfully submitted as follows : ")
                .replace("@greeting@", "Thank You").replace("@data@", data);
    }

    private String getRow(String head, String value) {
        return "<tr>\n" + "<td style=\"width: 351px;\"><strong>" + head + "</strong></td>\n"
                + "<td style=\"width: 10px;\">:</td>\n" + "<td style=\"width: 435px;\">" + value + "</td>\n" + "</tr>";
    }

}
