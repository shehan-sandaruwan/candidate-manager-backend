package lk.syscolabs.candidatemanager.email;

import lk.syscolabs.candidatemanager.util.AuthMessage;
import lk.syscolabs.candidatemanager.util.TemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;

public class Authenticate implements EmailMessage {

    @Autowired
    private TemplateLoader templateLoader;

    @Override
    public String getMessage(Object o) {
        AuthMessage authMessage = (AuthMessage) o;
        String template = templateLoader.getTemplate("authenticate");

        return template.replace("@description@", "Loging URL for user " + authMessage.getEmail() + " as follows. Click on/ Copy-paste on new tab to continue.")
                .replace("@data@", authMessage.getUrl());
    }
}
