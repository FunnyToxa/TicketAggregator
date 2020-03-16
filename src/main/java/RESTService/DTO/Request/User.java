package RESTService.DTO.Request;

import javax.persistence.*;
import java.util.List;

/**
 * класс пользователя
 */
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(length = 64)
    private String token;

    @Column(length = 64)
    private String mail;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserRequest> requests;

    public User(String token){
        this.token = token;
    }

    /**
     * Констуктор по умолчанию
     */
    public User(){

    }

    /**
     * добавление запроса пользователю
     * @param request
     */
    public void addRequest(UserRequest request){
        requests.add(request);
    }

    /**
     * геттер mail
     * @return
     */
    public String getMail() {
        return mail;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User)obj;
        return this.token.equals(user.getToken());
    }

    @Override
    public int hashCode() {
        return this.token.hashCode();
    }
}
