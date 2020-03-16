package RESTService.Service;

import RESTService.DTO.Response.Company;
import RESTService.DTO.Response.Station;
import RESTService.DTO.Response.Trip;
import RESTService.Utils.HttpRequestUtils;
import RESTService.jsonClasses.Yandex.Destination.Destination;
import RESTService.jsonClasses.Yandex.Destination.Settlement;
import RESTService.jsonClasses.Yandex.Response.Response;
import com.google.gson.Gson;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

public  class YandexApiService implements ApiService {
    private List<Settlement> settlements;

    private final static String yandexStringURLStations = "https://api.rasp.yandex.net/v3.0/stations_list/?apikey=f3504e64-a75e-418f-b2e7-d765a8027d2f";

    public YandexApiService() throws IOException {
        this(HttpRequestUtils.executeGetRequest(yandexStringURLStations));
    }

    public YandexApiService(String jsonCities){
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
    public RESTService.DTO.Settlement findSettlement(String name){
        Settlement tmpStmt = settlements.stream()
                .filter(settlement -> settlement.getTitle().toLowerCase().contains(name.toLowerCase()))
                .findFirst()
                .orElse(null);
        if (tmpStmt == null)
            return null;
        return new RESTService.DTO.Settlement(tmpStmt.getTitle(), tmpStmt.getCodes().getYandexCode());
    }

    /**
     * Преобразование ответа в виде строки JSON в список поездок
     * @param stringResponse
     * @return
     */
    @Override
    public List<Trip> convertToListTrips(String stringResponse){
        if (stringResponse == null)
            return null;
        Gson gson = new Gson();
        Response response = gson.fromJson(stringResponse, Response.class);

        List<Trip> result = new ArrayList<>();

        response.getSegments().forEach(segment -> {
            Trip tmpTrip = new Trip();
            tmpTrip.setCompany(new Company(segment.getThread().getCarrier().getTitle(), segment.getThread().getCarrier().getCode().toString()));
            tmpTrip.setDepartment(segment.getDeparture());
            tmpTrip.setStationFrom(new Station(segment.getFrom().getTitle(), segment.getFrom().getCode()));
            tmpTrip.setStationTo(new Station(segment.getTo().getTitle(), segment.getTo().getCode()));
            tmpTrip.setNumber(segment.getThread().getNumber());
            //т.к. есть ограничения при получении цены - делаем их рендомными
            Random r = new Random();
            tmpTrip.setPrice(r.nextInt(5000)+5000);

            result.add(tmpTrip);
        });

        return result;
    }
}
