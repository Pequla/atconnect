package app.admintools.atconnect.repo;

import app.admintools.atconnect.entity.RemoteServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RemoteServerRepository extends JpaRepository<RemoteServer, Long> {

    Boolean existsByUuid(String uuid);

    Optional<RemoteServer> findByUuid(String uuid);
}
