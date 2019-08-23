package arkpas.kursspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    public void configure (HttpSecurity security) throws Exception {
        security.formLogin().defaultSuccessUrl("/knights", true);
        security.csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();
        security.authorizeRequests()
                .antMatchers("/register").permitAll();
        security.authorizeRequests().anyRequest().authenticated();
        security.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();

    }

    @Autowired
    public void securityUsers (AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("SELECT username, password, active FROM player_information WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM role WHERE username = ?");

    }

}

