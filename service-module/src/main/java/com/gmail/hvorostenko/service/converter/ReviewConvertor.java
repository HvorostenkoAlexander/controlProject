package com.gmail.hvorostenko.service.converter;

import com.gmail.hvorostenko.repository.model.Review;
import com.gmail.hvorostenko.service.model.ReviewDTO;
import liquibase.pro.packaged.S;
import lombok.SneakyThrows;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
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

    @SneakyThrows
    public ReviewDTO convert(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setComment(review.getComment());
        LocalDate date = LocalDate.ofInstant(
                review.getDateAdded().toInstant(), ZoneId.systemDefault());
        reviewDTO.setDateAdded(String.valueOf(date));
        reviewDTO.setStatusShow(review.getStatusShow());
        reviewDTO.setNameAuthor(review.getUser().getName());
        reviewDTO.setSurnameAuthor(review.getUser().getSurname());
        reviewDTO.setPatronymicAuthor(review.getUser().getPatronymic());
        return reviewDTO;
    }
}
