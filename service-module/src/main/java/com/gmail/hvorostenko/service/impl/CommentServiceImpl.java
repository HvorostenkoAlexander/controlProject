package com.gmail.hvorostenko.service.impl;

import com.gmail.hvorostenko.repository.CommentRepository;
import com.gmail.hvorostenko.repository.model.Comment;
import com.gmail.hvorostenko.service.CommentService;
import com.gmail.hvorostenko.service.converter.CommentConvertor;
import com.gmail.hvorostenko.service.model.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentConvertor commentConvertor;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public List<CommentDTO> findAllByArticle(String id) {
        List<Comment> comments = commentRepository.findAllByArticle(Long.parseLong(id));
        return commentConvertor.convert(comments);
    }

    @Override
    @Transactional
    public Integer delete(List<String> idComments) {
        int resultDelete = commentRepository.delete(idComments);
        return resultDelete;
    }
}
