package lk.syscolabs.candidatemanager.config;

import lk.syscolabs.candidatemanager.util.LoggingDispatcherServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class LoggingInterceptConfig {

    @Bean
    public ServletRegistrationBean dispatcherRegistration() {
        return new ServletRegistrationBean(dispatcherServlet());
    }

    @Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
    @ConditionalOnProperty(name = "live.logger.active", havingValue = "true")
    public DispatcherServlet dispatcherServlet() {
        return new LoggingDispatcherServlet();
    }


}
