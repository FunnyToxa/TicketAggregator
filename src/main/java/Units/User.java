package Units;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * класс пользователя
 */
@Entity
@Table(name = "Users")
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int userId;
    private String firstName;
    private String lastName;
    private int age;
    @Id
    @Column(length = 64)
    private String mail;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<UserRequest> requests;

    public User(String firstName, String lastName, int age, String mail){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.mail = mail;
        requests = new ArrayList<UserRequest>();
    }

    public User(){



    }

    /**
     * добавление запроса пользователю
     * @param request
     */
    public void addRequest(UserRequest request){
        requests.add(request);
    }
}
