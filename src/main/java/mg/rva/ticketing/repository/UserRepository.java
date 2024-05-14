package mg.rva.ticketing.repository;

import mg.rva.ticketing.model.Ticket;
import mg.rva.ticketing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value="SELECT u FROM User u WHERE u.id=?1")
    User findById(int id);

    @Query(value="SELECT t FROM Ticket t WHERE t.assignedUser=?1")
    List<Ticket> findTicketsByUser(User user);

    @Query(value="SELECT CASE WHEN COUNT(u)> 0 THEN TRUE ELSE FALSE END FROM User u " +
            "WHERE (LOWER(u.username) LIKE LOWER(?1) OR LOWER(u.email) LIKE LOWER(?2))")
    Boolean existsByUsernameOrEmail(String username, String email);

    @Query(value="SELECT CASE WHEN COUNT(u)> 0 THEN TRUE ELSE FALSE END FROM User u " +
            "WHERE u.id<>?1 AND (LOWER(u.username) LIKE LOWER(?2) OR LOWER(u.email) LIKE LOWER(?3))")
    Boolean existsByUsernameOrEmail(Integer id, String username, String email);
}
