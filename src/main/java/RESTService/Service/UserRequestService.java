package RESTService.Service;

import RESTService.DTOUnits.Request.User;
import RESTService.DTOUnits.Request.UserRequest;
import RESTService.DTOUnits.UserRequestDTO;
import RESTService.Repository.UserRepository;
import RESTService.Repository.UserRequestRepository;
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

    public UserRequest saveUserRequest(UserRequest userRequest){
        //если пользователь уже есть в базе - подставляем его в запрос
        Optional<User> userOpt = userRepository.findById(userRequest.getUser().getToken());
        if (userOpt.isPresent())
            userRequest.setUser(userOpt.get());
        return userRequestRepository.save(userRequest);
    }
}
