package org.example.dto.user;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String token;
    private String tokenType;
    private String email;
    private String role;

}
