package RESTService.Repository;

import RESTService.DTOUnits.Request.UserRequest;
import RESTService.Utils.DateUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Repository
public class UserRequestCustomRepositoryImpl implements UserRequestCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final int MINUTES_TO_COMPARE = 5;

    @Override
    public boolean isUserRequestExist(UserRequest userRequest) {
        //создаем обьекты для поиска по критериям
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class); //Long, т.к. интересно только кол-во
        Root<UserRequest> root = criteria.from(UserRequest.class);
        //критерии
        Predicate equalMail = builder.equal(root.get("user"), userRequest.getUser()); //проверяем юзера
        Predicate dateGreaterThen = builder.greaterThan(root.get("requestDate"), DateUtils.rollMinutes(new Date(), -MINUTES_TO_COMPARE));
        Predicate equalFrom =builder.equal(root.get("fromCityName"), userRequest.getFromCityName());
        Predicate equalTo = builder.equal(root.get("toCityName"), userRequest.getToCityName());
        Predicate equalTripDate = builder.equal(root.get("tripDate"), userRequest.getTripDate());
        //суммируем критерии
        criteria.select(builder.count(root)).where(builder.and(equalMail, equalFrom, equalTo, equalTripDate, dateGreaterThen));
        //выполняем запрос
        TypedQuery<Long> query = entityManager.createQuery(criteria);
        List<Long> countList = query.getResultList();

        if (countList.size() > 0 && countList.get(0) > 0)
            return true;
        return false;
    }
}
