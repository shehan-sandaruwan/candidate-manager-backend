package lk.syscolabs.candidatemanager.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerMapperConfig {

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setCustomFieldMapper(
                (source, destination, sourceFieldValue, classMap, fieldMapping) -> sourceFieldValue == null);
        return mapper;
    }
}
