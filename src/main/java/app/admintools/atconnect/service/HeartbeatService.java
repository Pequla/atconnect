package app.admintools.atconnect.service;

import app.admintools.atconnect.entity.RemoteServer;
import app.admintools.atconnect.model.HeartbeatModel;
import app.admintools.atconnect.repo.RemoteServerRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HeartbeatService {

    private final RemoteServerRepository repository;
    private final RemoteServerService service;

    public void registerHeartbeat(HttpServletRequest request, RemoteServer server, LocalDateTime when) {
        // Retrieve XFR
        String address = request.getHeader("X-Forwarded-For");
        if (address == null) {
            address = request.getRemoteAddr();
        }

        server.setAddress(address);
        server.setHeartbeatAt(when);
        repository.save(server);
    }

    public HeartbeatModel getServerHeartbeat(String uuid) {
        RemoteServer server = service.retrieveByUUID(uuid);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime minusSeconds = now.minusSeconds(12);

        HeartbeatModel model = new HeartbeatModel();
        model.setUuid(server.getUuid());
        model.setOnline(
                (server.getHeartbeatAt().isAfter(minusSeconds) || server.getHeartbeatAt().isEqual(minusSeconds))
                        && (server.getHeartbeatAt().isBefore(now) || server.getHeartbeatAt().isEqual(now))
        );
        return model;
    }
}
