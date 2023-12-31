package ee.sda.ticketingsystem.dto;

import ee.sda.ticketingsystem.entity.Ticket;
import lombok.Data;
import lombok.experimental.Accessors;


import java.util.Date;
@Data
@Accessors
public class CommentDTO {

    private Long commentId;
    private Ticket ticket;
    private String content;
    private Date commentDate;
}
