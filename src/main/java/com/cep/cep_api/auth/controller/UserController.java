package com.cep.cep_api.auth.controller;

import com.cep.cep_api.auth.dto.UsersResponse;
import com.cep.cep_api.auth.repository.ProfileRepository;
import com.cep.cep_api.auth.repository.UsersRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersRepository usersRepository;
    private final ProfileRepository profileRepository;

    public UserController(UsersRepository usersRepository, ProfileRepository profileRepository) {
        this.usersRepository = usersRepository;
        this.profileRepository = profileRepository;
    }

    @GetMapping
    public List<UsersResponse> allUsers() {
        return usersRepository.findAll()
                .stream()
                .map(user -> new UsersResponse(user.getProfile().getName(), user.getEmail()))
                .toList();
    }
  }
