package mg.rva.ticketing.service;

import mg.rva.ticketing.model.Ticket;
import mg.rva.ticketing.model.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User findById(int id);
    public User createOrUpdateUser(User user);
    public List<Ticket> findTicketsByUser(User user);
    public boolean existsByUsernameOrEmail(String username, String email);
    public boolean existsByUsernameOrEmail(Integer id, String username, String email);
}
