package lk.syscolabs.candidatemanager.config;

import lk.syscolabs.candidatemanager.util.CustomBasicAuthenticationEntryPoint;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String realName = "MY_TEST_REALM";
    @Autowired
    private Log appLog;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            appLog.error(e.getStackTrace());
        }
        for (int i = 0; i < Integer.parseInt(properties.getProperty("api.user.count")); i++) {
            String[] userDetails = properties.getProperty("api.user." + i).split(",");
            auth.inMemoryAuthentication().withUser(userDetails[0]).password("{noop}" + userDetails[1]).roles(userDetails[2]);
        }

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            appLog.error(e.getStackTrace());
        }
        for (int i = 0; i < Integer.parseInt(properties.getProperty("api.auth.url.count")); i++) {
            String[] authRoute = properties.getProperty("api.auth.url." + i).split(",");
            http.csrf().disable().authorizeRequests().antMatchers(authRoute[0]).hasRole(authRoute[1]).and().httpBasic()
                    .realmName(realName).authenticationEntryPoint(getBasicAuthEntryPoint()).and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        }

    }

    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
