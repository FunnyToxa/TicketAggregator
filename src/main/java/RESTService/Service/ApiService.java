package RESTService.Service;

import RESTService.DTOUnits.Response.Trip;
import RESTService.DTOUnits.Settlement;

import java.util.List;

public interface ApiService {
    void initSettlements(String jsonCities);

    Settlement findSettlement(String name);

    List<Trip> convertToListTrips(String response);
}
