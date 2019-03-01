package lk.syscolabs.candidatemanager.email;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class EmailServiceMock implements EmailService {

    @Value("${email.mock.path}")
    private String emailMockPath;
    @Autowired
    private Log appLog;

    @Async
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        Map<String, String> email = new HashMap<>();
        email.put("to", to);
        email.put("subject", subject);
        email.put("text", text);

        String log = "<p>to : " + to + "&nbsp;&nbsp;subject : " + subject + "&nbsp;&nbsp;at : " + date + "<p>" + text
                + "======================================================================================================================";
        File yourFile = new File(emailMockPath);
        try {
            if (yourFile.createNewFile()) appLog.info(yourFile.toString() + " created.");
            Files.write(Paths.get(emailMockPath), log.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            appLog.error(e.getStackTrace());
        }

        appLog.info("\033[1;32mto : " + to + "  subject : " + subject + "  at : " + date + "\033[0m");
    }
}
