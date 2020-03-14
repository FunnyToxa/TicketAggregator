package RESTService.Config;

import RESTService._Utils.TicketAggrUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Configuration
public class SpringConf {

    @Bean
    public TicketAggrUtils getTicketAggrUtils(){
        return new TicketAggrUtils();
    }

    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
