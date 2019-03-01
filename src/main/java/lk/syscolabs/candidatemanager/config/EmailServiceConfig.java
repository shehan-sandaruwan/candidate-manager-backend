package lk.syscolabs.candidatemanager.config;

import lk.syscolabs.candidatemanager.email.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class EmailServiceConfig {
    @Bean
    @ConditionalOnProperty(name = "email.service", havingValue = "spring", matchIfMissing = true)
    public EmailServiceImpl googleEmailService() {
        return new EmailServiceImpl();
    }

//    @Bean
//    @ConditionalOnProperty(name = "email.service", havingValue = "spring")
//    public EmailService yahooEmailService() {
//        return new EmailServiceImpl();
//    }

    @Bean
    public EmailMessage submission() {
        return new Submission();
    }

    @Bean
    public EmailMessage authenticate() {
        return new Authenticate();
    }
}
