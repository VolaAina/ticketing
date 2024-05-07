package mg.rva.ticketing.service.impl;

import mg.rva.ticketing.model.Ticket;
import mg.rva.ticketing.model.User;
import mg.rva.ticketing.repository.UserRepository;
import mg.rva.ticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User createOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<Ticket> findTicketsByUser(User user) {
        return userRepository.findTicketsByUser(user);
    }

    @Override
    public boolean existsByUsernameOrEmail(String username, String email){
        return userRepository.existsByUsernameOrEmail(username, email);
    }

    @Override
    public boolean existsByUsernameOrEmail(Integer id, String username, String email){
        return userRepository.existsByUsernameOrEmail(id, username, email);
    }

}