package RESTService._UtilsDB;

import RESTService.Units.Request.User;
import RESTService.Units.Request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserRequestService {
    @Autowired
    private UserRequestRepository userRequestRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean isUserRequestExist(UserRequest userRequest){
        return userRequestRepository.isUserRequestExist(userRequest);
    }

    public void saveUserRequest(UserRequest userRequest){
        //если пользователь уже есть в базе - подставляем его в запрос
        User user = userRepository.findById(userRequest.getUser().getToken()).get();
        if (user != null)
            userRequest.setUser(user);
        userRequestRepository.save(userRequest);
    }
}