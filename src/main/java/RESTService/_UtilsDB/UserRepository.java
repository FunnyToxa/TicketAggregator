package RESTService._UtilsDB;

import RESTService.Units.Request.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    
}
