package arkpas.kursspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;


@Configuration
@Import(SecurityConfig.class)
public class MainConfig {


}


