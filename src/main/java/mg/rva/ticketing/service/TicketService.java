package mg.rva.ticketing.service;

import mg.rva.ticketing.model.Ticket;

import java.util.List;

public interface TicketService {
    public List<Ticket> findAll();
    public Ticket findById(int id);
    public Ticket createOrUpdateTicket(Ticket ticket);
    public void deleteTicket(Ticket ticket);
}
