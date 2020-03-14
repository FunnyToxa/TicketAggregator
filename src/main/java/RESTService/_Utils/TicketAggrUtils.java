package RESTService._Utils;

import RESTService._UtilsDB.UserRequestDAO;
import RESTService.Units.UserRequest;
import RESTService.Controllers.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketAggrUtils {

    @Autowired
    private UserRequestDAO userRequestDAO;

    /**
     * Проверка - есть ли запрос в базе
     * @param userRequestDTO
     * @return
     */
    public boolean checkRequestInDB(UserRequestDTO userRequestDTO){
        if (userRequestDAO.isUserRequestExist(getUserRequest(userRequestDTO)))
            return true;
        return false;
    }

    public String addResponseToDB(UserRequestDTO userRequestDTO){
        if (userRequestDAO.isUserRequestExist(getUserRequest(userRequestDTO)))
            return "Запрос выполнен меньше 5 минут назад!";
        else {
            saveToDB(userRequestDTO);
            return  "Запрос добавлен в базу";
        }
    }

    private UserRequest getUserRequest (UserRequestDTO userRequestDTO){
        return new UserRequest(userRequestDTO.getMail(),
                                userRequestDTO.getFrom(),
                                userRequestDTO.getTo(),
                                userRequestDTO.getTripDate());
}

    /**
     * сохранение в базе
     * @param userRequestDTO
     */
    private void saveToDB(UserRequestDTO userRequestDTO){
        userRequestDAO.save(getUserRequest(userRequestDTO));
    }
}
