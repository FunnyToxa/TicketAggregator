package RESTService.Repository;

import RESTService.DTOUnits.Request.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRequestRepository extends JpaRepository<UserRequest, String>, UserRequestCustomRepository {

}
