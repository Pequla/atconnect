package app.admintools.atconnect.repo;

import app.admintools.atconnect.entity.Heartbeat;
import app.admintools.atconnect.entity.RemoteServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface HeartbeatRepository extends JpaRepository<Heartbeat, Long> {
    boolean existsByServerAndCreatedAtBetween(RemoteServer server, LocalDateTime start, LocalDateTime end);
}
