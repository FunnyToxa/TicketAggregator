package RESTService.DTO.Entries;

import RESTService.DTO.Response.Trip;

import java.util.List;

public class UserResponseEntry extends ResponseEntry {
    private List<Trip> trips;

    public UserResponseEntry(List<Trip> trips){
        this.trips = trips;
    }

    public List<Trip> getTrips() {
        return trips;
    }
}
