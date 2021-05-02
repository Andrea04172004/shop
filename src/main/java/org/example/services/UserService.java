package org.example.services;

import org.example.dto.user.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto signup(UserDto userDto);
    UserDto findUserByEmail(String email);
    UserDto updateProfile(String email, UserDto userDto);
    UserDto changePassword(UserDto userDto, String newPassword);
}
