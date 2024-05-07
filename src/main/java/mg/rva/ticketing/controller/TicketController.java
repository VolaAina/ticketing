package mg.rva.ticketing.controller;

import mg.rva.ticketing.model.Ticket;
import mg.rva.ticketing.model.User;
import mg.rva.ticketing.service.TicketService;
import mg.rva.ticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> getTickets() {
        try {
            List<Ticket> tickets = ticketService.findAll();
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> getTicketById(@PathVariable int id) {
        try {
            Ticket ticket = ticketService.findById(id);
            if (ticket != null) {
                return new ResponseEntity<>(ticket, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket with ID " + id + " NOT FOUND");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> createTicket(@RequestBody Ticket ticket) {
        try {
            Ticket ticketSaved = ticketService.createOrUpdateTicket(ticket);
            return new ResponseEntity<>(ticketSaved, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> updateTicket(@PathVariable int id, @RequestBody Ticket ticket) {
        try {
            Ticket ticketTemp = ticketService.findById(id);
            if (ticketTemp != null) {
                ticketTemp.setTitle(ticket.getTitle());
                ticketTemp.setDescription(ticket.getDescription());
                ticketTemp.setStatus(ticket.getStatus());
                ticketTemp = ticketService.createOrUpdateTicket(ticketTemp);
                return new ResponseEntity<>(ticketTemp, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket with ID " + id + " NOT FOUND");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/assign/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> assignTicketToUser(@PathVariable int id, @PathVariable int userId) {
        try {
            Ticket ticket = ticketService.findById(id);
            if (ticket != null) {
                User user = userService.findById(userId);
                if (user != null) {
                    ticket.setAssignedUser(user);
                    ticket = ticketService.createOrUpdateTicket(ticket);
                    return new ResponseEntity<>(ticket, HttpStatus.OK);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + userId + " NOT FOUND");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket with ID " + id + " NOT FOUND");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> deleteTicket(@PathVariable int id) {
        try {
            Ticket ticket = ticketService.findById(id);
            if (ticket != null) {
                ticketService.deleteTicket(ticket);
                return new ResponseEntity<>(id, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket with ID " + id + " NOT FOUND");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
