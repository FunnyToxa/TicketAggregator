package RESTService.Repository;

import RESTService.DTOUnits.Request.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
