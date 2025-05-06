package ru.chermashentsev.cloudcomputing.dto.response.product.review;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductReviewResponseDTO {

    private long id;

    private String eanProduct;

    private int rating;

    private boolean recommendToFriend;

    private boolean chooseSimilarAgain;

    private String userId;

}
