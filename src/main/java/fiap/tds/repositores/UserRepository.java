package fiap.tds.repositores;


import fiap.tds.models.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public User findByUsername(String username) {
        return find("username", username).firstResult();
    }

    public void persistUser(User user){
        user.persist();
    }

    public User findByPassword(String password){
        return find("password", password).firstResult();
    }




    //public User findById(Long id){
    //    return find("id", id).firstResult();
    //}

}