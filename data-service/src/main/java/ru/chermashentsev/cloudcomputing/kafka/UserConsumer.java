package ru.chermashentsev.cloudcomputing.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.chermashentsev.cloudcomputing.dto.request.product.CreateProductRequestDTO;
import ru.chermashentsev.cloudcomputing.dto.request.user.CreateUserRequestDTO;
import ru.chermashentsev.cloudcomputing.entity.Product;
import ru.chermashentsev.cloudcomputing.entity.User;
import ru.chermashentsev.cloudcomputing.mapper.UserMapper;
import ru.chermashentsev.cloudcomputing.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserConsumer {


    private final UserMapper userMapper;
    private final UserService userService;

    @KafkaListener(topics = "${app.kafka.user-topic}")
    public void listen(CreateUserRequestDTO requestDTO) {
        User user = userMapper.toModel(requestDTO);

        userService.save(user);
        log.info("User created: {}", user);
    }

}
