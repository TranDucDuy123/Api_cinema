package com.example.cinema.services;
import com.example.cinema.dtos.request.UserCreateRequest;
import com.example.cinema.dtos.request.UserUpdateRequest;
import com.example.cinema.dtos.response.UserResponse;
import com.example.cinema.entities.User;
import com.example.cinema.exceptions.AppException;
import com.example.cinema.exceptions.ErrorCode;
import com.example.cinema.mapper.UserMaper;
import com.example.cinema.mapper.UserMaperImpl;
import com.example.cinema.repositories.RoleRepository;
import com.example.cinema.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserSevice {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    UserMaper mapper;
    PasswordEncoder passwordEncoder;
    private UserMaperImpl userMaperImpl;

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAll(){
            return userRepository.findAll();
    }
//   create user
    public UserResponse createUser(UserCreateRequest request){
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return UserResponse.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .phone(user.getPhone())
                .fullName(user.getFullName())
                .build();
    }
//
    public UserResponse updateUser(Integer id, UserUpdateRequest request){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
//
        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));
        userRepository.save(user);
        return UserResponse.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .phone(user.getPhone())
                .fullName(user.getFullName())
                .roles(user.getRoles())
                .build();
    }

    public UserResponse getUser(Integer id){
        User user = userRepository.findById(id).orElseThrow(() ->new RuntimeException("User not found"));
        userRepository.save(user);
        return UserResponse.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .phone(user.getPhone())
                .fullName(user.getFullName())
                .roles(user.getRoles())
                .build();
    }
    public String test(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
