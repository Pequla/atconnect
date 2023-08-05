package app.admintools.atconnect.controller;

import app.admintools.atconnect.entity.ServerLog;
import app.admintools.atconnect.model.NewModel;
import app.admintools.atconnect.service.ServerLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/log")
@RequiredArgsConstructor
public class ServerLogController {

    private final ServerLogService service;

    @GetMapping(path = "/{uuid}")
    public List<ServerLog> retrieveAll(@PathVariable String uuid, @RequestParam(name = "last", required = false) Long id) {
        return service.getAllLogs(uuid, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createLog(@RequestBody NewModel model) {
        service.saveNewLog(model);
    }
}
