package org.example.dto.user;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String email;
    private String password;
}
