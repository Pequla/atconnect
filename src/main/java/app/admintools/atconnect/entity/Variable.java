package app.admintools.atconnect.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "variable")
public class Variable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variable_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String value;
}
