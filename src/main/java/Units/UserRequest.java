package Units;

import javax.persistence.*;
import java.io.DataInputStream;
import java.util.Date;

/**
 * класс запросов от пользователей
 */
@Entity
@Table(name = "UserRequests")
public class UserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int requestId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mail")
    private User user;
//    private City startCity;
//    private City finishCity;
    private Date date;

    public UserRequest(User user){
        this.date = new Date();
        this.user = user;
    }

    public UserRequest(){

    }
}
