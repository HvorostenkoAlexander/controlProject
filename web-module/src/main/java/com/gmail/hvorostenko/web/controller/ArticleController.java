package com.gmail.hvorostenko.web.controller;

import com.gmail.hvorostenko.repository.model.Article;
import com.gmail.hvorostenko.service.ArticleService;
import com.gmail.hvorostenko.service.CommentService;
import com.gmail.hvorostenko.service.UserService;
import com.gmail.hvorostenko.service.model.ArticleDTO;
import com.gmail.hvorostenko.service.model.CommentDTO;
import com.gmail.hvorostenko.service.model.PageDTO;
import com.gmail.hvorostenko.service.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;
    private final UserService userService;


    @GetMapping
    public String getArticlesPage(Model model, @RequestParam(name = "page", defaultValue = "1") Integer pageCurrent) {
        PageDTO<ArticleDTO> pageDTO = articleService.findAllSortDate(pageCurrent);
        model.addAttribute("page", pageDTO);
        return "articles";
    }


    @GetMapping("/id")
    public String getArticlePage(@RequestParam("id") String id, Model model) {
        ArticleDTO article = articleService.findById(id);
        List<CommentDTO> comments = commentService.findAllByArticle(id);
        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        return "article";
    }

    @PostMapping("/delete")
    public RedirectView deleteArticle(@RequestParam("idCheckedDelete") List<String> idArticles,
                                      final RedirectAttributes redirectAttributes) {
        if (!idArticles.isEmpty()) {
            Integer articlesCountDelete = articleService.deleteArticles(idArticles);
            redirectAttributes.addFlashAttribute("articlesCountDelete", articlesCountDelete);
        }
        return new RedirectView("");
    }

    @GetMapping("/add")
    public String addArticle(Model model) {
        model.addAttribute("article", new Article());
        return "article_add";
    }

    @PostMapping("/add")
    public String addArticleSubmit(@ModelAttribute("article") @Valid ArticleDTO articleDTO,
                                   BindingResult bindingResult,
                                   Principal principal,
                                   final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "article_add";
        }
        String nameUser = principal.getName();
        UserDTO user = userService.getUserByEmail(nameUser);
        articleService.add(user.getId(), articleDTO);
        return "redirect:/articles";
    }


    @PostMapping("/update")
    public String addUserSubmit(@ModelAttribute("article") ArticleDTO articleDTO,
                                @RequestParam("idArticle") String idArticle,
                                HttpServletRequest request) {
        articleService.update(articleDTO, idArticle);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
