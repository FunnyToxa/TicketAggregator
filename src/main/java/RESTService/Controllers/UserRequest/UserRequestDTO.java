package RESTService.Controllers.UserRequest;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDTO {
    private String token;
    private String from;
    private String to;
    //дата в запросе пользователя
    private String tripDate;
    //дата, когда был создан запрос
    private Date requestDate;

    public UserRequestDTO(String token, String from, String to, String tripDateString) throws ParseException {
        this.token = token;
        this.from = from;
        this.to = to;
        this.requestDate = new Date();

//        this.tripDate = getDateFromString(tripDateString);
        this.tripDate = tripDateString;

    }

    private Date getDateFromString(String stringDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(stringDate);
    }

    public String getToken() {
        return token;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getTripDate() {
        return tripDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }
}


