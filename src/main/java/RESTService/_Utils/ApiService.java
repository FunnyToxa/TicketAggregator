package RESTService._Utils;

import RESTService.Units.Response.Trip;
import RESTService.Units.Settlement;

import java.util.List;

public interface ApiService {
    void initSettlements(String jsonCities);

    Settlement findSettlement(String name);

    List<Trip> convertToListTrips(String response);
}
