package lk.syscolabs.candidatemanager.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DTOMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
