package ru.chermashentsev.cloudcomputing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//@Entity
//@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate birthday;

    private String gender;

    private String discountCardNumber;

    private boolean isHaveChildren;

    private boolean isMarried;

    private String education;

    private String city;

}
