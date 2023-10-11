package ee.sda.ticketingsystem.controller;

import ee.sda.ticketingsystem.dto.TicketDTO;
import ee.sda.ticketingsystem.entity.Ticket;
import ee.sda.ticketingsystem.exception.TicketNotFoundException;
import ee.sda.ticketingsystem.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/ticket")
public class TicketController {

    private TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Integer id) {
        Ticket ticket = ticketService.getTicketById(id);
        return ResponseEntity.ok().body(ticket);
    }






}
