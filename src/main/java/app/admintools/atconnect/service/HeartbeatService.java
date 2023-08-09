package app.admintools.atconnect.service;

import app.admintools.atconnect.entity.Heartbeat;
import app.admintools.atconnect.entity.RemoteServer;
import app.admintools.atconnect.model.HeartbeatModel;
import app.admintools.atconnect.repo.HeartbeatRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HeartbeatService {

    private final HeartbeatRepository repository;
    private final RemoteServerService service;

    public void registerHeartbeat(HttpServletRequest request, RemoteServer server, LocalDateTime when) {
        // Retrieve XFR
        String address = request.getHeader("X-Forwarded-For");
        if (address == null) {
            address = request.getRemoteAddr();
        }

        Heartbeat heartbeat = new Heartbeat();
        heartbeat.setServer(server);
        heartbeat.setAddress(address);
        heartbeat.setCreatedAt(when);
        repository.save(heartbeat);
    }

    public HeartbeatModel getServerHeartbeat(String uuid) {
        RemoteServer server = service.retrieveByUUID(uuid);
        LocalDateTime plusSeconds = LocalDateTime.now().plusSeconds(30);

        HeartbeatModel model = new HeartbeatModel();
        model.setUuid(server.getUuid());
        model.setOnline(repository.existsByCreatedAtBefore(plusSeconds));
        return model;
    }
}
