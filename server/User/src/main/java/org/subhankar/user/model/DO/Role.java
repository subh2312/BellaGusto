package org.subhankar.user.model.DO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String id;
    private String code;
    private String name;
    private String description;
}
