package com.gmail.hvorostenko.web.controller;

import com.gmail.hvorostenko.repository.model.Review;
import com.gmail.hvorostenko.repository.model.User;
import com.gmail.hvorostenko.service.PageService;
import com.gmail.hvorostenko.service.impl.ReviewServiceImpl;
import com.gmail.hvorostenko.service.model.PageDTO;
import com.gmail.hvorostenko.service.model.ReviewDTO;
import com.gmail.hvorostenko.service.model.RoleDTO;
import com.gmail.hvorostenko.service.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewServiceImpl reviewService;


    @GetMapping
    public String getReviewsPage(Model model, @RequestParam(name = "page", defaultValue = "1") Integer pageCurrent) {
        PageDTO<ReviewDTO> pageDTO = reviewService.findAll(pageCurrent);
        model.addAttribute("page", pageDTO);
        return "reviews";
    }

    @PostMapping("/delete")
    public RedirectView deleteReview(@RequestParam("idCheckedDelete") List<String> idReview,
                                   Principal principal,
                                   final RedirectAttributes redirectAttributes) {
        if (!idReview.isEmpty()) {
            Integer reviewCountDelete = reviewService.delete(idReview);
            redirectAttributes.addFlashAttribute("reviewCountDelete", reviewCountDelete);
        }
        return new RedirectView("");
    }

    @GetMapping("/add")
    public String addReview(Model model) {
        model.addAttribute("review", new Review());
        return "review_add";
    }

    @PostMapping("/add")
    public String addReview(@ModelAttribute("review") @Valid ReviewDTO reviewDTO,
                                  BindingResult bindingResult,
                            Principal principal,
                                     final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "review_add";
        }
        String nameUser = principal.getName();
        reviewService.add(reviewDTO, nameUser);
        return "redirect:/articles";
    }
}
