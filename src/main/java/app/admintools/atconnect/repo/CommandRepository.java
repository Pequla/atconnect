package app.admintools.atconnect.repo;

import app.admintools.atconnect.entity.Command;
import app.admintools.atconnect.entity.RemoteServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {

    List<Command> findAllByServerAndExecutedAtIsNull(RemoteServer server);
}
