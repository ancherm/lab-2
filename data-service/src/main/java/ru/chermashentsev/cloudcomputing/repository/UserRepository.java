package ru.chermashentsev.cloudcomputing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chermashentsev.cloudcomputing.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
