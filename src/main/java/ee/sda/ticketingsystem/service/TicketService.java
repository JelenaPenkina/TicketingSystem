package ee.sda.ticketingsystem.service;

import ee.sda.ticketingsystem.dto.TicketDTO;
import ee.sda.ticketingsystem.entity.Ticket;
import ee.sda.ticketingsystem.entity.User;
import ee.sda.ticketingsystem.enums.Priority;
import ee.sda.ticketingsystem.enums.Status;
import ee.sda.ticketingsystem.exception.TicketNotFoundException;
import ee.sda.ticketingsystem.exception.UserNotFoundException;
import ee.sda.ticketingsystem.hydrator.TicketHydrator;
import ee.sda.ticketingsystem.repository.TicketRepository;
import ee.sda.ticketingsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class TicketService {

    @Autowired
    public TicketService(TicketRepository ticketRepository, TicketHydrator ticketHydrator, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketHydrator = ticketHydrator;
        this.userRepository = userRepository;
    }

    private TicketRepository ticketRepository;
    private TicketHydrator ticketHydrator;
    private UserRepository userRepository;


    @Value("${ticket.defaultStatus}")
    private String defaultStatus;

    @Value("${ticket.defaultPriority}")
    private String defaultPriority;

    @Transactional
    public TicketDTO createTicket(TicketDTO ticketDTO) {

        User user = userRepository.findById(ticketDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with id:" + ticketDTO.getUserId() + " not found"));

        Ticket ticket = ticketHydrator.convertToEntity(ticketDTO)
                .setStatus(Status.valueOf(defaultStatus))
                .setPriority(Priority.valueOf(defaultPriority))
                .setCreationDate(new Date())
                .setUser(user);
        System.out.println("Ticket created!");

        Ticket savedTicket = ticketRepository.save(ticket);

        return ticketHydrator.convertToDTO(savedTicket);
    }

    public List<TicketDTO> getAllTicket() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(ticket -> ticketHydrator.convertToDTO(ticket))
                .collect(Collectors.toList());
    }

    // Kuigi pakkus algselt teha Optional<Ticket> .orElseThrow() asemel
    public Ticket getTicketById(Integer id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id:" + id + " not found"));
    }

    @Transactional
    public TicketDTO editTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketRepository.findById(ticketDTO.getTicketId())
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id:" + ticketDTO.getTicketId() + " not found"))
                .setTitle(ticketDTO.getTitle())
                .setDescription(ticketDTO.getDescription())
                .setStatus(ticketDTO.getStatus())
                .setPriority(ticketDTO.getPriority());

        return ticketHydrator.convertToDTO(ticket);
    }


}
