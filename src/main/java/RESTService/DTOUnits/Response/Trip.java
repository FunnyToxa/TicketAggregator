package RESTService.DTOUnits.Response;

import RESTService.DTOUnits.Request.UserRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Класс поездок
 */
@Entity
@Table(name = "trips")
public class Trip {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tripId;
    //номер рейса
    private String number;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stationFromCode")
    private Station stationFrom;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stationToCode")
    private Station stationTo;
    private Date department;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyCode")
    private Company company;
    private int price;

//    @Getter
//    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "trips_userRequest",
            joinColumns = @JoinColumn(name = "tripId"),
            inverseJoinColumns = @JoinColumn(name = "requestId")
    )
    private Collection<UserRequest> userRequestsTrips;

    private Date responseDate;

    public Trip() {
        this.responseDate = new Date();
    }

//        @ManyToMany(mappedBy = "trips")
//    private Set<UserRequest> userRequests;


    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public Station getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(Station stationFrom) {
        this.stationFrom = stationFrom;
    }

    public Station getStationTo() {
        return stationTo;
    }

    public void setStationTo(Station stationTo) {
        this.stationTo = stationTo;
    }

    public Date getDepartment() {
        return department;
    }

    public void setDepartment(Date department) {
        this.department = department;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public Collection<UserRequest> getUserRequestsTrips() {
        return userRequestsTrips;
    }

    public void setUserRequestsTrips(Collection<UserRequest> userRequestsTrips) {
        this.userRequestsTrips = userRequestsTrips;
    }
}

