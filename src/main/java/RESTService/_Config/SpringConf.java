package RESTService._Config;

import RESTService._Utils.ApiService;
import RESTService._Utils.TicketAggrUtils;
import RESTService._Utils.YandexApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.TimeZone;

@Configuration
public class SpringConf {

    @Bean
    public TicketAggrUtils getTicketAggrUtils() throws IOException {
        return new TicketAggrUtils();
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
