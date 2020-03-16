package RESTService.Service;

import RESTService.DTOUnits.UserRequestResponse;
import RESTService.DTOUnits.Response.Trip;
import RESTService.DTOUnits.Settlement;
import RESTService.DTOUnits.Request.UserRequest;
import RESTService.DTOUnits.UserRequestDTO;
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

    /**
     * сохранение запроса в базе
     * @param userRequestDTO
     */
    public UserRequest saveRequest(UserRequestDTO userRequestDTO) throws ParseException{
        return userRequestService.saveUserRequest(getUserRequest(userRequestDTO));
    }


    /**
     * Преобразование userRequestDTO в UserRequest
     * @param userRequestDTO
     * @return UserRequest
     * @throws ParseException
     */
    private UserRequest getUserRequest (UserRequestDTO userRequestDTO) throws ParseException {
        return new UserRequest(userRequestDTO.getToken(),
                                userRequestDTO.getFrom(),
                                userRequestDTO.getTo(),
                                userRequestDTO.getTripDate());
}

    /**
     * поиск и сохранение поездок
     * @param userRequestDTO
     * @return
     */
    public UserRequestResponse searchAndSaveTrips(UserRequestDTO userRequestDTO, UserRequest userRequest){
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
            tripService.saveTrips(trips, userRequest);

            return new UserRequestResponse("Успешно", trips);

        } catch (IOException e) {
            return new UserRequestResponse("Ошибка при поиске: " + e.getMessage(), null);
        }
    }
}
