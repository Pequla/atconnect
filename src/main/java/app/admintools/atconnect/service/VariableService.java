package app.admintools.atconnect.service;

import app.admintools.atconnect.repo.VariableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VariableService {

    private final VariableRepository repository;

    public String getAdminToken() {
        return repository.findByName("admin.token")
                .orElseThrow(() -> new RuntimeException("No admin token specified"))
                .getValue();
    }
}
