package RESTService.Utils;

import RESTService.DBUtils.UserRequestDAO;
import RESTService.Units.UserRequest;
import RESTService.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketAggrUtils {
//    public void TestMethod(){
//
//        UserRequestDAO urDAO = new UserRequestDAO();
//
////        User user = new User("Petr", "Velikiy", 31, "nety");
//        User user = new User("test@mail.ru");
//
//        UserRequest ur1 = new UserRequest(user);
//        urDAO.save(ur1);
//
//        UserRequest ur2 = new UserRequest(user);
//        urDAO.save(ur2);
//    }

    //TODO: попробовать переделать на Autowared
//    @Autowired
    private UserRequestDAO userRequestDAO;

    public TicketAggrUtils(){
         userRequestDAO = new UserRequestDAO();
    }

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
