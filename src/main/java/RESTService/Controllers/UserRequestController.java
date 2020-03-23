package RESTService.Controllers;

import RESTService.DTO.Request.UserRequest;
import RESTService.DTO.Response.Trip;
import RESTService.DTO.RequestResponse;
import RESTService.DTO.Entries.UserResponseEntry;
import RESTService.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userRequest")
public class UserRequestController {

    @Autowired
    private TicketService ticketService;

    /**
     * получаем обратно запрос пользователя в виде объекта
     * @param token
     * @param from
     * @param to
     * @return
     */
    @GetMapping
    public RequestResponse getUserRequest(@RequestParam String token,
                                                          @RequestParam String from,
                                                          @RequestParam String to,
                                                          @RequestParam String tripDate){

        //проверяем авторизацию
        try {
            if (!ticketService.checkToken(token))
                return new RequestResponse("Ошибка авторизации. Токен не действителен!");
        } catch (CannotCreateTransactionException e){ //обработка ошибки если БД стала недоступна во время работы приложения
            return new RequestResponse("Сервис временно недоступен. Просьба повторить попытку через некоторое время.");
        } catch (RuntimeException e){
            return new RequestResponse("Неизвестная ошибка!");
        }



        //отрабатываем запрос
        try {
            UserRequest userRequest = new UserRequest(token, from, to, tripDate);

            //проверяем - есть ли запрос в базе
            boolean isRequestExist = ticketService.checkRequestOnExist(userRequest);
            if (isRequestExist) {
                userRequest = ticketService.findLastUserRequestInDB(userRequest);
                List<Trip> trips = new ArrayList<Trip>(userRequest.getTrips());
                if (trips.size() > 0)
                    return new RequestResponse(new UserResponseEntry(trips));
                else
                    isRequestExist = false;
            }
            //повторно смотрим условие (т.к. если запрос есть в базе но нет поездок - пробуем искать заново)
            if (!isRequestExist){
                //добавление запроса в базу
                UserRequest dbUserRequest = ticketService.saveRequest(userRequest);
                //выполянем поиск и формируем ответ
                return ticketService.searchAndSaveTrips(userRequest);
            }
        } catch (ParseException e) {
            return new RequestResponse("Ошибка при получении данных: " + e.getMessage());
        }
        return new RequestResponse("Неизвестная ошибка!");
    }
}
