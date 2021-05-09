package com.gmail.hvorostenko.service.impl;

import com.gmail.hvorostenko.repository.CommentRepository;
import com.gmail.hvorostenko.repository.model.Comment;
import com.gmail.hvorostenko.service.CommentService;
import com.gmail.hvorostenko.service.converter.CommentConvertor;
import com.gmail.hvorostenko.service.model.CommentDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentConvertor commentConvertor;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentConvertor commentConvertor, CommentRepository commentRepository) {
        this.commentConvertor = commentConvertor;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public List<CommentDTO> findAllByArticle(String id) {
        List<Comment> comments = commentRepository.findAllByArticle(Long.parseLong(id));
        return commentConvertor.convert(comments);
    }
}
