package lk.syscolabs.candidatemanager.email;


import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
public class EmailServiceImpl {

    @Autowired
    public JavaMailSender mailSender;
    @Autowired
    private Log appLog;

    @Async

    public void sendSimpleMessage(String to, String subject, String text) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessage.setContent(text, "text/html");
            helper.setTo(to);
            helper.setSubject(subject);

        } catch (MessagingException e) {
            e.printStackTrace();
            appLog.error(e.getStackTrace());
        }

        mailSender.send(mimeMessage);

    }

}