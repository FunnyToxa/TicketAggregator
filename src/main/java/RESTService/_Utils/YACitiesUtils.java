package RESTService._Utils;

import com.google.gson.Gson;
import jsonClasses.Yandex.Destination;
import jsonClasses.Yandex.Settlement;

import java.io.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

public  class YACitiesUtils implements CitiesUtils {
    private List<Settlement> settlements;

    public YACitiesUtils(String jsonCities){
        initSettlements(jsonCities);
    }

    /**
     * получение городов Яндекс-Расписания
     * @return
     * @throws IOException
     */
    private Destination getCities(String jsonCities) {
        Gson gson = new Gson();
        return gson.fromJson(jsonCities, Destination.class);
    }

    /**
     * получение списка станций с кодами
     */
    @Override
    public void initSettlements(String jsonCities){
        //получаем города
        System.out.println("Получаем список городов");
        Destination destination = this.getCities(jsonCities);
        settlements = destination.getCountries().stream()
                .map(country -> country.getRegions())
                .flatMap(List::stream)
                .map(region -> region.getSettlements())
                .flatMap(List::stream)
                .collect(toList());
        System.out.println("загружено городов:" + settlements.size());
    }

    /**
     * поиск станции по имени
     * @param name
     * @return
     */
    @Override
    public Settlement findSettlement(String name){
        return settlements.stream()
                .filter(settlement -> settlement.getTitle().toLowerCase().contains(name.toLowerCase()))
                .findFirst()
                .orElse(null);
    }
}
