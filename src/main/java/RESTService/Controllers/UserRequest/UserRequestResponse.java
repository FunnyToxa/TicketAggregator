package RESTService.Controllers.UserRequest;

import RESTService.Units.Response.Trip;

import java.util.List;

public class UserRequestResponse {
    private String message;
    private List<Trip> trips;

    public UserRequestResponse(String message, List<Trip> trips) {
        this.message = message;
        this.trips = trips;
    }

    public String getMessage() {
        return message;
    }

    public List<Trip> getTrips() {
        return trips;
    }
}
