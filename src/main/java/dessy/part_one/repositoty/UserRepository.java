package dessy.part_one.repositoty;

import dessy.part_one.model.entity.User;
import dessy.part_one.model.service.UserServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameAndPassword(String useraname, String password);

    @Query("select u.username from User u order by u.username asc")
    List<String> findAllUserNames();

    Optional<User> findByUsername(String useraname);


}
