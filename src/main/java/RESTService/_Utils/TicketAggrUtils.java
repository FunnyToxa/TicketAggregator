package RESTService._Utils;

import RESTService.Controllers.UserRequest.UserRequestResponse;
import RESTService.Units.Response.Trip;
import RESTService.Units.Settlement;
import RESTService.Units.Request.UserRequest;
import RESTService.Controllers.UserRequest.UserRequestDTO;
import RESTService._DB.TripService;
import RESTService._DB.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


@Component
public class TicketAggrUtils {
    @Autowired
    private ApiService apiService;

    @Autowired
    private UserRequestService userRequestService;

    @Autowired
    private TripService tripService;

    /**
     * Проверка - был ли уже такой запрос
     * @param userRequestDTO
     * @return
     */
    public boolean checkRequestOnExist(UserRequestDTO userRequestDTO){
        try {
            if (userRequestService.isUserRequestExist(getUserRequest(userRequestDTO)))
                return true;
        } catch (ParseException e) {
            return false;
        }
        return false;
    }

    public String saveRequest(UserRequestDTO userRequestDTO) throws ParseException{
        if (userRequestService.isUserRequestExist(getUserRequest(userRequestDTO)))
            return "Запрос выполнен меньше 5 минут назад!";
        else {
            this.saveToDB(userRequestDTO);
            return  "Запрос добавлен в базу";
        }
    }

    private UserRequest getUserRequest (UserRequestDTO userRequestDTO) throws ParseException {
        return new UserRequest(userRequestDTO.getToken(),
                                userRequestDTO.getFrom(),
                                userRequestDTO.getTo(),
                                userRequestDTO.getTripDate());
}

    /**
     * сохранение в базе
     * @param userRequestDTO
     */
    private void saveToDB(UserRequestDTO userRequestDTO) throws ParseException {
        userRequestService.saveUserRequest(getUserRequest(userRequestDTO));
    }

    private void saveToDB(List<Trip> trips){

    }

    /**
     *
     * @param userRequestDTO
     * @return
     */
    public UserRequestResponse searchAndSaveTrips(UserRequestDTO userRequestDTO){
        Settlement cityFrom = apiService.findSettlement(userRequestDTO.getFrom());
        if (cityFrom == null)
            return new UserRequestResponse("Не удалось найти город отправления", null);
        Settlement cityTo = apiService.findSettlement(userRequestDTO.getTo());
        if (cityTo == null)
            return new UserRequestResponse("Не удалось найти город прибытия", null);
        try {
            String stringResponse = HttpRequestUtils.executeGetRequest(
                        "https://api.rasp.yandex.net/v3.0/search/?"
                                + "apikey=f3504e64-a75e-418f-b2e7-d765a8027d2f"
                                + "&transport_types=plane"
                                + "&from=" + cityFrom.getCode()
                                + "&to=" + cityTo.getCode()
                                + "&date=" + userRequestDTO.getTripDate()
            );

            List<Trip> trips = apiService.convertToListTrips(stringResponse);
            tripService.saveTrips(trips);

            return new UserRequestResponse("Успешно", trips);

        } catch (IOException e) {
            return new UserRequestResponse("Ошибка при поиске: " + e.getMessage(), null);
        }
    }
}
