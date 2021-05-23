package com.gmail.hvorostenko.service;

import com.gmail.hvorostenko.service.model.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> findAllByArticle(String id);
}
