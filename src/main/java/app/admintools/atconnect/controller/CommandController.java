package app.admintools.atconnect.controller;

import app.admintools.atconnect.entity.Command;
import app.admintools.atconnect.model.NewModel;
import app.admintools.atconnect.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/command")
@RequiredArgsConstructor
public class CommandController {

    private final CommandService service;

    @GetMapping(path = "/{uuid}")
    public List<Command> getAllCommands(@PathVariable String uuid) {
        return service.getAllNotExecuted(uuid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCommand(@RequestBody NewModel model) {
        service.addNewCommand(model);
    }
}
