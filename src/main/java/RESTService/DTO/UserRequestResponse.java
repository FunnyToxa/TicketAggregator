package RESTService.DTO;

import RESTService.DTO.Response.Trip;

import java.util.List;

/**
 * Описание ответа пользователю на запрос
 * создержит:
 * сообщение - message
 * список поездок - trips
 *
 */
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
