package com.gmail.hvorostenko.service.converter;

import com.gmail.hvorostenko.repository.model.Review;
import com.gmail.hvorostenko.service.model.ReviewDTO;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewConvertor {
    public Review convert(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setComment(reviewDTO.getComment());
        review.setDateAdded(new Date());
        review.setStatusShow(reviewDTO.getStatusShow());

        return review;
    }


    public List<ReviewDTO> convert(List<Review> reviews) {
        return reviews.stream().map(review -> {
            return convert(review);
        }).collect(Collectors.toList());
    }

    public ReviewDTO convert(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setComment(review.getComment());
        Date date = DateUtils.truncate(review.getDateAdded(),
                java.util.Calendar.DAY_OF_MONTH);
        reviewDTO.setDateAdded(String.valueOf(date));
        reviewDTO.setStatusShow(review.getStatusShow());
        reviewDTO.setNameAuthor(review.getUser().getName());
        reviewDTO.setSurnameAuthor(review.getUser().getSurname());
        reviewDTO.setPatronymicAuthor(review.getUser().getPatronymic());
        return reviewDTO;
    }
}
