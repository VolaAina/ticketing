package mg.rva.ticketing.controller;

import mg.rva.ticketing.model.Ticket;
import mg.rva.ticketing.model.User;
import mg.rva.ticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getUsers() {
        try {
            List<User> users = userService.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        try {
            User user = userService.findById(id);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " NOT FOUND");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/ticket")
    public ResponseEntity<?> getUserTickets(@PathVariable int id) {
        try {
            User user = userService.findById(id);
            if (user != null) {
                List<Ticket> tickets = userService.findTicketsByUser(user);
                return new ResponseEntity<>(tickets, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " NOT FOUND");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            if (!(userService.existsByUsernameOrEmail(user.getUsername(), user.getEmail()))) {
                User userSaved = userService.createOrUpdateUser(user);
                return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or Email already used by another user");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user) {
        try {
            User userTemp = userService.findById(id);
            if (userTemp != null) {
                if (!(userService.existsByUsernameOrEmail(id, user.getUsername(), user.getEmail()))) {
                    userTemp.setUsername(user.getUsername());
                    userTemp.setEmail(user.getEmail());
                    userTemp = userService.createOrUpdateUser(userTemp);
                    return new ResponseEntity<>(userTemp, HttpStatus.OK);
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or Email already used by another user");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " NOT FOUND");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
