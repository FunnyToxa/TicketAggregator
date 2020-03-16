package RESTService.Service;

import RESTService.DTO.UserRequestResponse;
import RESTService.DTO.Response.Trip;
import RESTService.DTO.Settlement;
import RESTService.DTO.Request.UserRequest;
import RESTService.Utils.HttpRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * основной класс для работы с запросами пользователя и поездками
 */
@Component
public class TicketService {
    @Autowired
    private ApiService apiService;

    @Autowired
    private UserRequestService userRequestService;

    @Autowired
    private TripService tripService;

    /**
     * Проверка - был ли уже такой запрос
     * @param userRequest
     * @return
     */
    public boolean checkRequestOnExist(UserRequest userRequest){
        if (userRequestService.isUserRequestExist(userRequest))
            return true;
        return false;
    }

    /**
     * поиск userRequest в базе
     * @param userRequest
     * @return
     */
    public UserRequest findLastUserRequestInDB(UserRequest userRequest){
        Integer lastId = userRequestService.findLastRequestId(userRequest);
        if (lastId == -1)
            return null;
        return userRequestService.findUserRequest(lastId);
    }

    /**
     * сохранение запроса в базе
     * @param userRequest
     */
    public UserRequest saveRequest(UserRequest userRequest) throws ParseException{
        return userRequestService.saveUserRequest(userRequest);
    }


    /**
     * поиск и сохранение поездок
     * @param userRequest
     * @return
     */
    public UserRequestResponse searchAndSaveTrips(UserRequest userRequest){
        Settlement cityFrom = apiService.findSettlement(userRequest.getFromCityName());
        if (cityFrom == null)
            return new UserRequestResponse("Не удалось найти город отправления", null);
        Settlement cityTo = apiService.findSettlement(userRequest.getToCityName());
        if (cityTo == null)
            return new UserRequestResponse("Не удалось найти город прибытия", null);
        try {
            String stringResponse = HttpRequestUtils.executeGetRequest(
                        "https://api.rasp.yandex.net/v3.0/search/?"
                                + "apikey=f3504e64-a75e-418f-b2e7-d765a8027d2f"
                                + "&transport_types=plane"
                                + "&from=" + cityFrom.getCode()
                                + "&to=" + cityTo.getCode()
                                + "&date=" + userRequest.getTripDate()
            );

            List<Trip> trips = apiService.convertToListTrips(stringResponse);
            tripService.saveTrips(trips, userRequest);

            return new UserRequestResponse("Успешно", trips);

        } catch (IOException e) {
            return new UserRequestResponse("Ошибка при поиске: " + e.getMessage(), null);
        }
    }
}
