package org.example.services;

import org.example.domain.ShoppingCartEntity;
import org.example.domain.user.RoleEntity;
import org.example.domain.user.UserEntity;
import org.example.domain.user.UserRoles;
import org.example.dto.user.UserDto;
import org.example.exeptions.UserException;
import org.example.repositories.user.RoleRepository;
import org.example.repositories.user.UserRepository;
import org.example.utils.BusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusinessMapper businessMapper;


    @Override
    public UserDto signup(UserDto userDto) {
        UserEntity userEntity = userRepository.findByEmail(userDto.getEmail());
        if (userEntity != null) {
            throw new RuntimeException("User with such email is already exist");
        }
        userEntity = businessMapper.convertToUserEntity(userDto);
        Set<RoleEntity> roleEntities = new HashSet<>();
        roleEntities.add(roleRepository.findByRole("USER"));
        userEntity.setRoles(roleEntities);

        return businessMapper.convertToUserDto(userRepository.save(userEntity));
    }


    public UserDto findUserByEmail(String email) {
        return businessMapper.convertToUserDto(userRepository.findByEmail(email));
    }


    @Override
    public UserDto updateProfile(String email, UserDto userDto) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            user.setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName())
                    .setMobileNumber(userDto.getMobileNumber());
            return businessMapper.convertToUserDto(userRepository.save(user));
        }
        throw new RuntimeException("User with such email is not found exist");
    }


    @Override
    public UserDto changePassword(UserDto userDto, String newPassword) {
        UserEntity user = userRepository.findByEmail(userDto.getEmail());
        if (user != null) {
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            return businessMapper.convertToUserDto(userRepository.save(user));
        }
        throw new RuntimeException("User with such email is not found exist");
    }
}
