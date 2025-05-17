package ru.chermashentsev.cloudcomputing.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.chermashentsev.cloudcomputing.dto.response.report.AvgRatingAndCountReviewDTO;
import ru.chermashentsev.cloudcomputing.dto.response.report.CompanyRatingReportDTO;
import ru.chermashentsev.cloudcomputing.dto.response.report.ProductRecommendReportDTO;
import ru.chermashentsev.cloudcomputing.dto.response.report.RatingByGenderReportDTO;

import java.util.List;

@RestController
@RequestMapping("api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final RestTemplate restTemplate;

    @Value("${app.url.report}")
    private String dataServiceUrl;

    @GetMapping("get-avg-rating-and-count-reviews")
    public ResponseEntity<List<AvgRatingAndCountReviewDTO>> getAvgRatingAndCountReviews() {
        return restTemplate.exchange(dataServiceUrl +"/get-avg-rating-and-count-reviews",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
    }

    @GetMapping("get-recommend-percentage")
    public ResponseEntity<List<ProductRecommendReportDTO>> getRecommendPercentage() {
        return restTemplate.exchange(dataServiceUrl +"/get-recommend-percentage",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
    }

    @GetMapping("get-avg-by-gender")
    public ResponseEntity<List<RatingByGenderReportDTO>> getRatingByGender() {
        return restTemplate.exchange(dataServiceUrl +"/get-avg-by-gender",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
    }

    @GetMapping("get-company-rating")
    public ResponseEntity<List<CompanyRatingReportDTO>> getCompanyRating() {
        return restTemplate.exchange(dataServiceUrl +"/get-company-rating",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
    }


}
