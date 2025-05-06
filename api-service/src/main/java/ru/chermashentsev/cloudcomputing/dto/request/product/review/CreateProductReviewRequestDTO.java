package ru.chermashentsev.cloudcomputing.dto.request.product.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import static ru.chermashentsev.cloudcomputing.constants.ValidationMessage.RATING_RANGE_MESSAGE;
import static ru.chermashentsev.cloudcomputing.constants.ValidationMessage.USER_ID_NOT_BLANK_MESSAGE;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateProductReviewRequestDTO {

    @Min(value = 1, message = RATING_RANGE_MESSAGE)
    @Max(value = 5, message = RATING_RANGE_MESSAGE)
    private Integer rating;

    private boolean recommendToFriend;

    private boolean chooseSimilarAgain;

    @NotBlank(message = USER_ID_NOT_BLANK_MESSAGE)
    private String userId;

}
