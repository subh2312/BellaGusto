package org.subhankar.user.model.DO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="users")
    public class User {
        @Id
        @GeneratedValue(strategy= GenerationType.UUID)
        private String id;
        private String name;
        private String email;
        private String phone;
        private String password;
        private String gender;
        private String dob;
        private String salt;
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Role> roles = new HashSet<>();

        @Transient
        List<String> addressList = new ArrayList<>();
    }
