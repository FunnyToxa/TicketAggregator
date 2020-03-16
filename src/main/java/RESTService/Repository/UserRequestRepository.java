package RESTService.Repository;

import RESTService.DTO.Request.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRequestRepository extends JpaRepository<UserRequest, Integer>, UserRequestCustomRepository {

}
