package douglas.com.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import douglas.com.helpdesk.services.DBService;
import jakarta.annotation.PostConstruct;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;
    @PostConstruct
    public boolean instantiateTestDatabase() {
        if(value.equals("create")){
            this.dbService.instantiateTestDatabase();
        }
        return false;
    }
}
