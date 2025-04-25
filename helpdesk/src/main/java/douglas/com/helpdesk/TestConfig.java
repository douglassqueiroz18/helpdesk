package douglas.com.helpdesk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import douglas.com.helpdesk.services.DBServices;
import jakarta.annotation.PostConstruct;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBServices dbServices;
    @PostConstruct
    public void instantiateTestDatabase() {
        this.dbServices.instantiateTestDatabase();
    }
}
