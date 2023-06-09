package by.itacademy.spring.service;

import by.itacademy.spring.database.repository.UserRepository;
import by.itacademy.spring.mapper.CreateUserMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CreateUserMapper createUserMapper;
}
