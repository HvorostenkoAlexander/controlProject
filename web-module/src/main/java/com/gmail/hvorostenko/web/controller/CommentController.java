package com.gmail.hvorostenko.web.controller;

import com.gmail.hvorostenko.service.ArticleService;
import com.gmail.hvorostenko.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/delete")
    public String deleteArticle(@RequestParam("idCheckedDelete") List<String> idComments,
                                HttpServletRequest request) {
        if (!idComments.isEmpty()) {
            Integer commentsCountDelete = commentService.delete(idComments);
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}
