package app.admintools.atconnect.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HeartbeatModel {
    private String uuid;
    private Boolean online;
}
