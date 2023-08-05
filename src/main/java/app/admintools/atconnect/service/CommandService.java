package app.admintools.atconnect.service;

import app.admintools.atconnect.entity.Command;
import app.admintools.atconnect.entity.RemoteServer;
import app.admintools.atconnect.model.NewModel;
import app.admintools.atconnect.repo.CommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandService {

    private final CommandRepository repository;
    private final RemoteServerService service;

    public List<Command> getAllNotExecuted(String uuid) {
        LocalDateTime now = LocalDateTime.now();
        List<Command> commands = repository.findAllByServerAndExecutedAtIsNull(service.retrieveByUUID(uuid));
        commands.forEach(cmd -> cmd.setExecutedAt(now));
        return repository.saveAll(commands);
    }

    public void addNewCommand(NewModel model) {
        RemoteServer server = service.retrieveByUUID(model.getUuid());
        Command command = new Command();
        command.setValue(model.getValue());
        command.setServer(server);
        command.setCreatedAt(LocalDateTime.now());
        repository.save(command);
    }
}
