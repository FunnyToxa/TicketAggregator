package RESTService.Repository;

import RESTService.DTO.Request.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
