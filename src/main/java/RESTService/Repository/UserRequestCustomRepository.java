package RESTService.Repository;

import RESTService.DTOUnits.Request.UserRequest;

public interface UserRequestCustomRepository {
     boolean isUserRequestExist(UserRequest userRequest);
}
