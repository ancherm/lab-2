package ru.chermashentsev.cloudcomputing.api.report;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chermashentsev.cloudcomputing.dto.response.report.AvgRatingAndCountReviewDTO;
import ru.chermashentsev.cloudcomputing.dto.response.report.CompanyRatingReportDTO;
import ru.chermashentsev.cloudcomputing.dto.response.report.ProductRecommendReportDTO;
import ru.chermashentsev.cloudcomputing.dto.response.report.RatingByGenderReportDTO;
import ru.chermashentsev.cloudcomputing.service.report.ReportService;

import java.util.List;

@RestController
@RequestMapping("api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("get-avg-rating-and-count-reviews")
    public ResponseEntity<List<AvgRatingAndCountReviewDTO>> getAvgRatingAndCountReview() {
        return ResponseEntity.ok(reportService.getAvgRatingAndCountReview());
    }

    @GetMapping("get-recommend-percentage")
    public ResponseEntity<List<ProductRecommendReportDTO>> getRecommendPercentage() {
        return ResponseEntity.ok(reportService.getProductRecommendReport());
    }

    @GetMapping("get-avg-by-gender")
    public ResponseEntity<List<RatingByGenderReportDTO>> getAvgByGender() {
        return ResponseEntity.ok(reportService.getRatingByGenderReport());
    }

    @GetMapping("get-company-rating")
    public ResponseEntity<List<CompanyRatingReportDTO>> getCompanyRating() {
        return ResponseEntity.ok(reportService.getCompanyRatingReport());
    }

}
