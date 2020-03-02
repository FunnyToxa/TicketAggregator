package RESTService.Units;

import javax.persistence.*;
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
    @JoinColumn(name = "mail")
    private User user;
    private String fromCityName;
    private String toCityName;
    private Date requestDate;
    private Date tripDate;

//    public UserRequest(User user){
//        this.requestDate = new Date();
//        this.user = user;
//    }

    public UserRequest(String mail, String fromCityName, String toCityName, Date tripDate){
        this.user = new User(mail);
        this.fromCityName = fromCityName;
        this.toCityName = toCityName;
        this.tripDate = tripDate;
        this.requestDate = new Date();
    }

    public UserRequest(String mail, String fromCityName, String toCityName, Date tripDate, Date requestDate){
        this.user = new User(mail);
        this.fromCityName = fromCityName;
        this.toCityName = toCityName;
        this.tripDate = tripDate;
        this.requestDate = requestDate;
    }

//    public UserRequest(){
//
//    }

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
}
