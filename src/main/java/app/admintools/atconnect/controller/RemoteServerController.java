package app.admintools.atconnect.controller;

import app.admintools.atconnect.model.RegistrationModel;
import app.admintools.atconnect.service.RemoteServerService;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/server")
@RequiredArgsConstructor
public class RemoteServerController {

    private final RemoteServerService service;

    @PostMapping(path = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNew(@RequestBody RegistrationModel model, HttpServletRequest request) throws AuthException {
        service.registerNewServer(model, request);
    }
}
