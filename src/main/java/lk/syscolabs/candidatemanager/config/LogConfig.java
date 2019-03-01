package lk.syscolabs.candidatemanager.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogConfig {
    @Bean
    public Log appLog() {
        return LogFactory.getLog("candidate-manager-custom-log");
    }
}
