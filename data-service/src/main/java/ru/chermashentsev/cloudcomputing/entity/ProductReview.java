package ru.chermashentsev.cloudcomputing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
//@Table(name = "productReviews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long productId;

    private int rating;

    private boolean recommendToFriend;

    private boolean chooseSimilarAgain;

    private long userId;

}
