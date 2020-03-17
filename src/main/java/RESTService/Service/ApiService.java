package RESTService.Service;

import RESTService.DTO.Response.Trip;
import RESTService.DTO.Settlement;

import java.util.List;

public interface ApiService {
    void initSettlements(String jsonCities);

    Settlement findSettlement(String name);

    List<Trip> convertToListTrips(String response);
}
