package com.gmail.hvorostenko.service;

import com.gmail.hvorostenko.service.model.PageDTO;
import com.gmail.hvorostenko.service.model.ReviewDTO;

import java.util.List;

public interface ReviewService {
    PageDTO<ReviewDTO> findAll(Integer pageCurrent);
    Integer delete(List<String> idReview);
    void add(ReviewDTO reviewDTO, String nameUser);
}
