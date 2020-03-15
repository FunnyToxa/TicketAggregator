package RESTService._UtilsDB;

import RESTService.Units.Request.UserRequest;
import RESTService._Utils.DateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public interface UserRequestRepository extends JpaRepository<UserRequest, String>, UserRequestCustomRepository {

}
