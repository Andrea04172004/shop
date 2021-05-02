package org.example.dto.user;

import lombok.*;

import javax.validation.constraints.NotEmpty;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignupRequest {
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String email;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String password;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String firstName;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String lastName;
}
