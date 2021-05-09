package com.gmail.hvorostenko.service.impl;

import com.gmail.hvorostenko.repository.ReviewRepository;
import com.gmail.hvorostenko.repository.model.Review;
import com.gmail.hvorostenko.service.ReviewService;
import com.gmail.hvorostenko.service.converter.ReviewConvertor;
import com.gmail.hvorostenko.service.model.ReviewDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewConvertor reviewConvertor;
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewConvertor reviewConvertor, ReviewRepository reviewRepository) {
        this.reviewConvertor = reviewConvertor;
        this.reviewRepository = reviewRepository;
    }

    @Override
    @Transactional
    public List<ReviewDTO> findAll(Integer pageCurrent) {
        List<Review> reviews = reviewRepository.findAll(pageCurrent);
        return reviewConvertor.convert(reviews);
    }

    @Override
    @Transactional
    public Integer delete(List<String> idReview) {
        int resultDelete = reviewRepository.delete(idReview);
        return resultDelete;
    }
}
