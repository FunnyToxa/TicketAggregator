import Units.UserRequest;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRequestDAO {
    public void save(UserRequest userRequest) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(userRequest);
        transaction.commit();
        session.close();
    }
}
