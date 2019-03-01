package lk.syscolabs.candidatemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CandidateManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CandidateManagerApplication.class, args);

    }
}
