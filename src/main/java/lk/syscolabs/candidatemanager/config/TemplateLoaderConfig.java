package lk.syscolabs.candidatemanager.config;

import lk.syscolabs.candidatemanager.util.TemplateLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemplateLoaderConfig {
    @Bean
    public TemplateLoader templateLoader() {
        return new TemplateLoader();
    }
}
