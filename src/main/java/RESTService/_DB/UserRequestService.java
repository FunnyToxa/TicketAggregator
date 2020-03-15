package RESTService._DB;

import RESTService.Units.Request.User;
import RESTService.Units.Request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


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
        Optional<User> userOpt = userRepository.findById(userRequest.getUser().getToken());
        if (userOpt.isPresent())
            userRequest.setUser(userOpt.get());
        userRequestRepository.save(userRequest);
    }
}
