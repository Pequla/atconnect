package app.admintools.atconnect.service;

import app.admintools.atconnect.entity.Command;
import app.admintools.atconnect.entity.RemoteServer;
import app.admintools.atconnect.model.NewModel;
import app.admintools.atconnect.repo.CommandRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandService {

    private final CommandRepository repository;
    private final HeartbeatService heartbeatService;
    private final RemoteServerService serverService;

    public List<Command> getAllNotExecuted(String uuid, HttpServletRequest request) {
        LocalDateTime now = LocalDateTime.now();
        RemoteServer server = serverService.retrieveByUUID(uuid);
        heartbeatService.registerHeartbeat(request, server, now);
        List<Command> commands = repository.findAllByServerAndExecutedAtIsNull(server);
        commands.forEach(cmd -> cmd.setExecutedAt(now));
        return repository.saveAll(commands);
    }

    public void addNewCommand(NewModel model) {
        RemoteServer server = serverService.retrieveByUUID(model.getUuid());
        Command command = new Command();
        command.setValue(model.getValue());
        command.setServer(server);
        command.setCreatedAt(LocalDateTime.now());
        repository.save(command);
    }
}
