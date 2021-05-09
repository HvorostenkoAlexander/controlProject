package com.gmail.hvorostenko.service;

import com.gmail.hvorostenko.service.model.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> findAll(Integer pageCurrent);
    Integer delete(List<String> idReview);
}
