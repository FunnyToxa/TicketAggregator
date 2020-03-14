package RESTService._UtilsDB;

import RESTService.Units.UserRequest;
import RESTService._Utils.DateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Component
public class UserRequestDAO {
    //Сколько минут запрос будет считаться повторяющимся
    //т.е. по истечение скольки минут аналогичный запрос будет по новой добавлен в базу и отправлен на поиск
    private final static int MINUTES_TO_COMPARE = 5;

    public void save(UserRequest userRequest) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(userRequest);
        transaction.commit();
        session.close();
    }

    public boolean isUserRequestExist(UserRequest userRequest) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        //создаем обьекты для поиска по критериям
        CriteriaBuilder builder = session.getCriteriaBuilder();
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
        Query<Long> query = session.createQuery(criteria);
        List<Long> countList = query.getResultList();

        if (countList.size() > 0 && countList.get(0) > 0)
            return true;
        return false;
    }
}
