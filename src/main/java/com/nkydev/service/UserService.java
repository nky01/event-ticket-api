package com.nkydev.service;

import com.nkydev.dto.user.UserRequestDTO;
import com.nkydev.dto.user.UserResponseDTO;
import com.nkydev.entity.enums.Role;
import com.nkydev.entity.User;
import com.nkydev.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(@Valid UserRequestDTO request) {
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        // password
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);
        return mapToResponseDTO(savedUser);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public UserResponseDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("user not found with id: " + id));

        return mapToResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO updateUser(Integer id, @Valid UserRequestDTO request) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("user not found with id: " + id));

        user.setName(request.name());
        user.setEmail(request.email());
        //password

        User updatedUser = userRepository.save(user);
        return mapToResponseDTO(updatedUser);
    }

    public void deleteUser(Integer id) {if (!userRepository.existsById(id)) {
        throw new RuntimeException("User not found with id: " + id);
    }
        userRepository.deleteById(id);
    }

    public UserResponseDTO mapToResponseDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()) ;
    }
}