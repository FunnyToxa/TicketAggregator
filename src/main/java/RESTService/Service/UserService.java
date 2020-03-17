package RESTService.Service;

import RESTService.DTO.Request.User;
import RESTService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUser(String token){
        Optional<User> userOpt = userRepository.findById(token);
        if (userOpt.isPresent())
            return userOpt.get();
        return null;
    }
}
