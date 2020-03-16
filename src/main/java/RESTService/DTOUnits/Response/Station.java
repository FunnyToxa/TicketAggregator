package RESTService.DTOUnits.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stations")
public class Station {
    private String stationName;
    @Id
    private String stationCode;

    @JsonIgnore
    @OneToMany(mappedBy = "stationFrom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Trip> tripsFrom;

    @JsonIgnore
    @OneToMany(mappedBy = "stationTo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Trip> tripsTo;

    public Station() {
    }

    public Station(String stationName, String stationCode) {
        this.stationName = stationName;
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public List<Trip> getTripsFrom() {
        return tripsFrom;
    }

    public void setTripsFrom(List<Trip> tripsFrom) {
        this.tripsFrom = tripsFrom;
    }

    public List<Trip> getTripsTo() {
        return tripsTo;
    }

    public void setTripsTo(List<Trip> tripsTo) {
        this.tripsTo = tripsTo;
    }
}
