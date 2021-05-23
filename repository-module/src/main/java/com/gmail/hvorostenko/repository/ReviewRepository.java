package com.gmail.hvorostenko.repository;

import com.gmail.hvorostenko.repository.model.Review;
import com.gmail.hvorostenko.repository.model.Role;

import java.util.List;

public interface ReviewRepository extends GenericRepository<Long, Review> {
    List<Review> findAll(Integer pageCurrent);
    int delete(List<String> idReview);
}
