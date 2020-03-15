package RESTService._DB;

import RESTService.Units.Request.UserRequest;

public interface UserRequestCustomRepository {
     boolean isUserRequestExist(UserRequest userRequest);
}
