package RESTService.Repository;

import RESTService.DTO.Request.UserRequest;

public interface UserRequestCustomRepository {
     boolean isUserRequestExist(UserRequest userRequest);
     Integer findId(UserRequest userRequest);
}
