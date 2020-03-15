package RESTService._DB;

import RESTService.Units.Request.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRequestRepository extends JpaRepository<UserRequest, String>, UserRequestCustomRepository {

}
