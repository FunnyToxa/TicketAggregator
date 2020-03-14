package RESTService._Utils;

import RESTService.jsonClasses.Yandex.Settlement;

public interface CitiesUtils {
    void initSettlements(String jsonCities);

    Settlement findSettlement(String name);
}
