package RESTService.Controllers;

import RESTService.DTOUnits.Request.UserRequest;
import RESTService.DTOUnits.UserRequestDTO;
import RESTService.DTOUnits.UserRequestResponse;
import RESTService.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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
        UserRequestDTO userRequestDTO = null;
        try {
            userRequestDTO = new UserRequestDTO(token, from, to, tripDate);

            UserRequestResponse requestResponse = null;

            //проверяем - есть ли запрос в базе
            if (ticketService.checkRequestOnExist(userRequestDTO)){
                //TODO: здесь должен быть код получения ответа по запросу из БД/кэша
            } else {
                //добавление запроса в базу
                UserRequest userRequest = ticketService.saveRequest(userRequestDTO);
                //выполянем поиск и формируем ответ
                requestResponse = ticketService.searchAndSaveTrips(userRequestDTO, userRequest);
            }
            return ResponseEntity.ok(requestResponse);
        } catch (ParseException e) {
            return ResponseEntity.ok(new UserRequestResponse("Ошибка при получении данных: " + e.getMessage() , null));
        }


    }
}