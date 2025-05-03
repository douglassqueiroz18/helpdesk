package douglas.com.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import douglas.com.helpdesk.services.DBService;
import jakarta.annotation.PostConstruct;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;
    @PostConstruct
    public void instantiateTestDatabase() {
        this.dbService.instantiateTestDatabase();
    }
}
