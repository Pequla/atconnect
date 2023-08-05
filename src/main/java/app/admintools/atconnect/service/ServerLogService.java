package app.admintools.atconnect.service;

import app.admintools.atconnect.entity.RemoteServer;
import app.admintools.atconnect.entity.ServerLog;
import app.admintools.atconnect.model.NewModel;
import app.admintools.atconnect.repo.ServerLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServerLogService {

    private final ServerLogRepository repository;
    private final RemoteServerService service;

    public List<ServerLog> getAllLogs(String uuid, Long id) {
        RemoteServer server = service.retrieveByUUID(uuid);
        if (id == null) return repository.findTop100ByServer(server);
        return repository.findAllByServerAndIdGreaterThan(server, id);
    }

    public void saveNewLog(NewModel model) {
        ServerLog log = new ServerLog();
        log.setServer(service.retrieveByUUID(model.getUuid()));
        log.setValue(model.getValue());
        log.setCreatedAt(LocalDateTime.now());
        repository.save(log);
    }
}
