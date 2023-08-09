package app.admintools.atconnect.controller;

import app.admintools.atconnect.model.HeartbeatModel;
import app.admintools.atconnect.service.HeartbeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(path = "/api/heartbeat")
public class HeartbeatController {

    private final HeartbeatService service;

    @GetMapping(path = "/{uuid}")
    public HeartbeatModel getHeartbeatForServer(@PathVariable String uuid) {
        return service.getServerHeartbeat(uuid);
    }
}
