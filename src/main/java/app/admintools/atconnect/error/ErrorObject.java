package app.admintools.atconnect.error;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorObject {
    private String name;
    private String message;
    private String path;
    private Long timestamp;
}
