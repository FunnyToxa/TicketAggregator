package RESTService.Repository;

import RESTService.DTO.Request.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserRequestRepository extends JpaRepository<UserRequest, Integer>, UserRequestCustomRepository {
    @Query(value = "SELECT count(ur) as count, function('date_format', max(ur.requestDate), '%Y-%m-%d') as date FROM UserRequest ur " +
            "WHERE TO_DAYS(NOW()) - TO_DAYS(ur.requestDate) < 30 GROUP BY function('date_format', ur.requestDate, '%Y, %m, %d')")
    List<Map<String,String>> getCountOfUserRequestsGroupedByDays();
}
