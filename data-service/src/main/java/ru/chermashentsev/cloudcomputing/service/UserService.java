package ru.chermashentsev.cloudcomputing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chermashentsev.cloudcomputing.constants.ErrorCode;
import ru.chermashentsev.cloudcomputing.entity.User;
import ru.chermashentsev.cloudcomputing.exception.UserNotFoundException;
import ru.chermashentsev.cloudcomputing.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User delete(long id) {
        User user = findUserById(id);

        userRepository.delete(user);

        return user;
    }

    public User findUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND.format(id)));
    }

}
