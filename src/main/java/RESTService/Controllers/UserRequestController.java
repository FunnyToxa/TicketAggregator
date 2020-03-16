package RESTService.Controllers;

import RESTService.DTO.Request.UserRequest;
import RESTService.DTO.Response.Trip;
import RESTService.DTO.UserRequestResponse;
import RESTService.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserRequestResponse> getUserRequest(@RequestParam String token,
                                                              @RequestParam String from,
                                                              @RequestParam String to,
                                                              @RequestParam String tripDate){
        UserRequest userRequest = null;
        try {
            userRequest = new UserRequest(token, from, to, tripDate);

            UserRequestResponse requestResponse = null;

            //проверяем - есть ли запрос в базе
            if (ticketService.checkRequestOnExist(userRequest)){
                userRequest = ticketService.findLastUserRequestInDB(userRequest);
                List<Trip> trips = new ArrayList<Trip>(userRequest.getTrips());
                requestResponse = new UserRequestResponse("Выгрузка по последнему запросу", trips);
            } else {
                //добавление запроса в базу
                UserRequest dbUserRequest = ticketService.saveRequest(userRequest);
                //выполянем поиск и формируем ответ
                requestResponse = ticketService.searchAndSaveTrips(userRequest);
            }
            return ResponseEntity.ok(requestResponse);
        } catch (ParseException e) {
            return ResponseEntity.ok(new UserRequestResponse("Ошибка при получении данных: " + e.getMessage() , null));
        }


    }
}
