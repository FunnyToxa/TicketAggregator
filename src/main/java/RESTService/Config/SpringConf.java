package RESTService.Config;

import RESTService.Service.ApiService;
import RESTService.Service.TicketService;
import RESTService.Service.YandexApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.TimeZone;

@Configuration
public class SpringConf {

    @Bean
    public TicketService getTicketAggrUtils() throws IOException {
        return new TicketService();
    }

    @Bean
    public ApiService getCitiesUtils() throws IOException {
        return new YandexApiService();
    }

    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
