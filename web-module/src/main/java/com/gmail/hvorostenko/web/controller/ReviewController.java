package com.gmail.hvorostenko.web.controller;

import com.gmail.hvorostenko.repository.model.Review;
import com.gmail.hvorostenko.service.PageService;
import com.gmail.hvorostenko.service.impl.ReviewServiceImpl;
import com.gmail.hvorostenko.service.model.ReviewDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/administrator/reviews")
public class ReviewController {

    private final PageService<Review> pageSepvise;
    private final ReviewServiceImpl reviewService;

    public ReviewController(PageService<Review> pageSepvise, ReviewServiceImpl reviewService) {
        this.pageSepvise = pageSepvise;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getReviewsPage(Model model, @RequestParam(name = "page", defaultValue = "1") Integer pageCurrent) {
        List<ReviewDTO> reviews = reviewService.findAll(pageCurrent);
        List<Integer> pageNumbers = pageSepvise.countPage(new Review());
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("reviews", reviews);
        return "reviews";
    }

    @PostMapping("/delete")
    public RedirectView deleteUser(@RequestParam("idCheckedDelete") List<String> idReview,
                                   Principal principal,
                                   final RedirectAttributes redirectAttributes) {
        if (!idReview.isEmpty()) {
            Integer reviewCountDelete = reviewService.delete(idReview);
            redirectAttributes.addFlashAttribute("reviewCountDelete", reviewCountDelete);
        }
        return new RedirectView("");
    }
}
