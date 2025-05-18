package ru.chermashentsev.cloudcomputing.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.chermashentsev.cloudcomputing.dto.request.user.CreateUserRequestDTO;
import ru.chermashentsev.cloudcomputing.dto.response.user.UserResponseDTO;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final KafkaTemplate<String, CreateUserRequestDTO> kafkaTemplate;

    @Value("${app.url.data-service}")
    private String dataServiceUrl;

    @Value("${app.kafka.user-topic}")
    private String userTopic;


    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody CreateUserRequestDTO requestDTO) {
        kafkaTemplate.send(userTopic, requestDTO);
        return ResponseEntity.ok("Successfully added user");
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<UserResponseDTO>> userResponseDTOs = restTemplate.exchange(
                dataServiceUrl + "/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return ResponseEntity.ok(userResponseDTOs.getBody());
    }
}
