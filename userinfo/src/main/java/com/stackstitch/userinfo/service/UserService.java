package com.stackstitch.userinfo.service;


import com.stackstitch.userinfo.dto.UserDTO;
import com.stackstitch.userinfo.entity.User;
import com.stackstitch.userinfo.mapper.UserMapper;
import com.stackstitch.userinfo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDTO addUser(UserDTO userDTO){
        User savedUser = userRepository.save(UserMapper.INSTANCE.mapUserDtoToUser(userDTO));
        return UserMapper.INSTANCE.mapUserToUserDto(savedUser);

    }

    public ResponseEntity<UserDTO> findByUserId(Integer id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDto(user.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
