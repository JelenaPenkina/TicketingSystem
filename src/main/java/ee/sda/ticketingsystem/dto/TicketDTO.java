package ee.sda.ticketingsystem.dto;

import ee.sda.ticketingsystem.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors
public class TicketDTO {

    private Integer ticketId;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;
    @NotNull(message = "Creation Date cannot be null")
    private Date creationDate;

    private String priority;

    private String category;

    @NotNull(message = "Status cannot be null")
    private Status status;

    @NotNull(message = "User cannot be null")
    private UserDTO user;
    private List<AttachmentDTO> attachments;
    private List<CommentDTO> comments;
}
