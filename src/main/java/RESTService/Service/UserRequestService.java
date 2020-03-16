package RESTService.Service;

import RESTService.DTO.Request.User;
import RESTService.DTO.Request.UserRequest;
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

    public UserRequest findUserRequest(Integer requestId){
        Optional<UserRequest> urOpt = userRequestRepository.findById(requestId);
        if (urOpt.isPresent())
            return urOpt.get();
        return null;
    }

    public Integer findLastRequestId(UserRequest userRequest){
        return userRequestRepository.findId(userRequest);
    }
}
