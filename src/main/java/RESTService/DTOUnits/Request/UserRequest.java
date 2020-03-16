package RESTService.DTOUnits.Request;

import RESTService.DTOUnits.Response.Trip;
import RESTService.Utils.DateUtils;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

/**
 * класс запросов от пользователей
 */
@Entity
@Table(name = "UserRequests")
public class UserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int requestId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "token")
    private User user;
    private String fromCityName;
    private String toCityName;
    private Date requestDate;
    private Date tripDate;

    @ManyToMany(mappedBy = "userRequestsTrips")
    private Collection<Trip> trips;

    public UserRequest() {
    }

    public UserRequest(String token, String fromCityName, String toCityName, String tripDate) throws ParseException {
        this.user = new User(token);
        this.fromCityName = fromCityName;
        this.toCityName = toCityName;
        this.tripDate = DateUtils.getDateFromString(tripDate);
        this.requestDate = new Date();
    }

    public int getRequestId() {
        return requestId;
    }

    public User getUser() {
        return user;
    }

    public String getFromCityName() {
        return fromCityName;
    }

    public String getToCityName() {
        return toCityName;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
