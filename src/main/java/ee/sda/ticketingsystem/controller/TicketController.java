package ee.sda.ticketingsystem.controller;

import ee.sda.ticketingsystem.dto.TicketDTO;
import ee.sda.ticketingsystem.entity.Ticket;
import ee.sda.ticketingsystem.exception.TicketNotFoundException;
import ee.sda.ticketingsystem.service.TicketService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

    TicketService ticketService;

    // localhost:8080/ticket/12
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Integer id) {
        Ticket ticket = ticketService.getTicketById(id);
        return ResponseEntity.ok().body(ticket);
    }
    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getAllTicket();
    }
    @PostMapping
    public ResponseEntity<?> createTicket(@Valid @RequestBody TicketDTO ticketDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Find error: " + bindingResult);
        }
        TicketDTO createdTicket = ticketService.createTicket(ticketDTO);
        return ResponseEntity.ok(createdTicket);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> editTicket(@PathVariable Integer id, @RequestBody TicketDTO updatedTicket) {
        try {
            updatedTicket.setTicketId(id);
            TicketDTO editedTicket = ticketService.editTicket(updatedTicket);
            return ResponseEntity.ok(editedTicket);
        } catch (TicketNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }


}
