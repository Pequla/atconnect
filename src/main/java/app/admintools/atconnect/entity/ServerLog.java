package app.admintools.atconnect.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "log")
public class ServerLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "server_id", nullable = false)
    private RemoteServer server;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
