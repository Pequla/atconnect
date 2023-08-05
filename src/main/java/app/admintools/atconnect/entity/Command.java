package app.admintools.atconnect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "command")
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "command_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "server_id", nullable = false)
    private RemoteServer server;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    private LocalDateTime executedAt;
}
