package app.admintools.atconnect.repo;

import app.admintools.atconnect.entity.RemoteServer;
import app.admintools.atconnect.entity.ServerLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerLogRepository extends JpaRepository<ServerLog, Long> {

    List<ServerLog> findTop100ByServer(RemoteServer server);

    List<ServerLog> findAllByServerAndIdGreaterThan(RemoteServer server, Long id);
}
