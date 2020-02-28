import Units.User;
import Units.UserRequest;

public class TicketAggregator {
    public void TestMethod(){

        UserRequestDAO urDAO = new UserRequestDAO();

        User user = new User("Petr", "Velikiy", 31, "nety");

        UserRequest ur1 = new UserRequest(user);
        urDAO.save(ur1);

        UserRequest ur2 = new UserRequest(user);
        urDAO.save(ur2);

    }
}
