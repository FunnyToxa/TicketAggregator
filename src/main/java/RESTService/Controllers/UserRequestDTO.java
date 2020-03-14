package RESTService.Controllers;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDTO {
    private String mail;
    private String from;
    private String to;
    //дата в запросе пользователя
    private Date tripDate;
    //дата, когда был создан запрос
    private Date requestDate;

    public UserRequestDTO(String mail, String from, String to, String tripDateString) throws ParseException {
        this.mail = mail;
        this.from = from;
        this.to = to;
        this.requestDate = new Date();

        this.tripDate = getDateFromString(tripDateString);

    }

    private Date getDateFromString(String stringDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(stringDate);
    }

    public String getMail() {
        return mail;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }
}


