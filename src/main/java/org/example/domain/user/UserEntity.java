package org.example.domain.user;

import lombok.*;
import lombok.experimental.Accessors;
import org.example.domain.ShoppingCartEntity;

import javax.persistence.*;
import java.util.Set;

@Builder
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String firstName;

    private String lastName;
    @Column(nullable = false, unique = true)
    private String mobileNumber;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roles;

    @OneToOne (fetch = FetchType.EAGER)
    private ShoppingCartEntity shoppingCartEntity;
}
