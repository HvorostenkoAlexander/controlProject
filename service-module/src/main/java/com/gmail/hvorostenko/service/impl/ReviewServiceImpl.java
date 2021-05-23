package com.gmail.hvorostenko.service.impl;

import com.gmail.hvorostenko.repository.ReviewRepository;
import com.gmail.hvorostenko.repository.model.Article;
import com.gmail.hvorostenko.repository.model.Review;
import com.gmail.hvorostenko.service.PageService;
import com.gmail.hvorostenko.service.ReviewService;
import com.gmail.hvorostenko.service.converter.ReviewConvertor;
import com.gmail.hvorostenko.service.model.PageDTO;
import com.gmail.hvorostenko.service.model.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewConvertor reviewConvertor;
    private final ReviewRepository reviewRepository;
    private PageService<Article> pageService;

    @Override
    @Transactional
    public PageDTO<ReviewDTO> findAll(Integer pageCurrent) {
        PageDTO<ReviewDTO> pageDTO = new PageDTO();
        List<Review> reviews = reviewRepository.findAll(pageCurrent);
        List<ReviewDTO> reviewDTO = reviewConvertor.convert(reviews);
        pageDTO.getEntityList().addAll(reviewDTO);
        List<Integer> pageNumbers = pageService.countPage(new Article());
        pageDTO.getPageNumbers().addAll(pageNumbers);
        return pageDTO;
    }

    @Override
    @Transactional
    public Integer delete(List<String> idReview) {
        int resultDelete = reviewRepository.delete(idReview);
        return resultDelete;
    }
}
