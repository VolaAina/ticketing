package mg.rva.ticketing.repository;

import mg.rva.ticketing.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value="SELECT t FROM Ticket t WHERE t.id=?1")
    Ticket findById(int id);
}
