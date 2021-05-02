package org.example.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.example.dto.ShoppingCartDto;

import java.util.Set;

@Accessors(chain = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private boolean isAdmin;
    private Set<RoleDto> roles;
    private ShoppingCartDto shoppingCartDto;
}
