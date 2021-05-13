package com.gmail.hvorostenko.web.controller;

import com.gmail.hvorostenko.repository.model.Article;
import com.gmail.hvorostenko.service.ArticleService;
import com.gmail.hvorostenko.service.CommentService;
import com.gmail.hvorostenko.service.PageService;
import com.gmail.hvorostenko.service.UserService;
import com.gmail.hvorostenko.service.model.ArticleDTO;
import com.gmail.hvorostenko.service.model.CommentDTO;
import com.gmail.hvorostenko.service.model.PageDTO;
import com.gmail.hvorostenko.service.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final ArticleService articleService;
    private final CommentService commentService;
    private final UserService userService;


    @GetMapping("/articles")
    public String getArticlesPage(Model model, @RequestParam(name = "page", defaultValue = "1") Integer pageCurrent) {
        PageDTO<ArticleDTO> pageDTO = articleService.findAllSortDate(pageCurrent);
        model.addAttribute("page", pageDTO);
        return "articles";
    }


    @GetMapping("/article")
    public String getArticlePage(@RequestParam("id") String id, Model model) {
        ArticleDTO article = articleService.findById(id);
        List<CommentDTO> comments = commentService.findAllByArticle(id);
        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        return "article";
    }

    @GetMapping("/profile")
    public String getProfileForm(Model model,
                                 Principal principal) {
        String nameUser = principal.getName();
        UserDTO user = userService.getUserByEmail(nameUser);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile-update")
    public String addUserSubmit(@ModelAttribute("user") UserDTO userDTO,
                                @RequestParam("idUser") String idUser,
                                final RedirectAttributes redirectAttributes) {
        UserDTO updateUser = userService.update(userDTO, idUser);
        redirectAttributes.addFlashAttribute("updateUser", updateUser);
        return "redirect:/customer/profile";
    }

    @PostMapping("/profile-update-password")
    public RedirectView updatePasswordUser(@RequestParam("idUser") List<String> idUser,
                                           final RedirectAttributes redirectAttributes) {
        List<UserDTO> updatePasswordUserDTO = userService.updatePasswordUser(idUser);
        redirectAttributes.addFlashAttribute("updatePasswordUserDTO", updatePasswordUserDTO);

        return new RedirectView("profile");
    }
}
