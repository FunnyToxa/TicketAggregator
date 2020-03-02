package RESTService;

import RESTService.Utils.TicketAggrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/userRequest")
public class UserRequestController {

    @Autowired
    private TicketAggrUtils ticketAggrUtils;

    /**
     * Тестируем работу сервиса
     * @return ответ что все ок :)
     */
//    @GetMapping
//    public BaseResponse showStatus(){
//        return new BaseResponse(HttpStatus.OK);
//    }

    /**
     * получаем обратно запрос пользователя в виде объекта
     * @param mail
     * @param from
     * @param to
     * @return
     */
    @GetMapping
    public ResponseEntity<String> getUserRequest(@RequestParam String mail,
                                                         @RequestParam String from,
                                                         @RequestParam String to,
                                                         @RequestParam String tripDate){
        UserRequestDTO userRequestDTO = null;
        try {
            userRequestDTO = new UserRequestDTO(mail, from, to, tripDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        return ResponseEntity.ok(ticketAggrUtils.addResponseToDB(userRequestDTO));
    }
}
