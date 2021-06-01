package com.gmail.hvorostenko.service.converter;

import com.gmail.hvorostenko.repository.model.Comment;
import com.gmail.hvorostenko.service.model.CommentDTO;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class CommentConvertor {

    public Comment convert(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setDate(new Date());
        comment.setContent(commentDTO.getContent());
        return comment;
    }

    public List<CommentDTO> convert(List<Comment> comments) {
        return comments.stream().map(comment -> {
            return convert(comment);
        }).collect(Collectors.toList());
    }

    public CommentDTO convert(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        LocalDate date = LocalDate.ofInstant(
                comment.getDate().toInstant(), ZoneId.systemDefault());
        commentDTO.setDate(String.valueOf(date));
        commentDTO.setContent(comment.getContent());
        commentDTO.setNameAuthor(comment.getUser().getName());
        commentDTO.setSurnameAuthor(comment.getUser().getSurname());
        return commentDTO;
    }
}
