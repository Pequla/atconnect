package app.admintools.atconnect.service;

import app.admintools.atconnect.entity.RemoteServer;
import app.admintools.atconnect.model.RegistrationModel;
import app.admintools.atconnect.repo.RemoteServerRepository;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RemoteServerService {

    private final RemoteServerRepository serverRepo;
    private final VariableService variableService;

    public void registerNewServer(RegistrationModel model, HttpServletRequest request) throws AuthException {
        if (serverRepo.existsByUuid(model.getUuid()))
            throw new RuntimeException("Server already registered");

        String token = request.getHeader("x-token");
        if (!token.equals(variableService.getAdminToken()))
            throw new AuthException("Invalid admin token");

        RemoteServer server = new RemoteServer();
        server.setUuid(model.getUuid());
        server.setCreatedAt(LocalDateTime.now());
        serverRepo.save(server);
    }

    public RemoteServer retrieveByUUID(String uuid) {
        return serverRepo.findByUuid(uuid).orElseThrow(() -> new RuntimeException("Server not registered"));
    }
}
