package com.gmail.hvorostenko.web.controller;

import com.gmail.hvorostenko.service.UserService;
import com.gmail.hvorostenko.service.model.ArticleDTO;
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
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping
    public String getProfileForm(Model model,
                                 Principal principal) {
        String nameUser = principal.getName();
        UserDTO user = userService.getUserByEmail(nameUser);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/update")
    public String addUserSubmit(@ModelAttribute("user") UserDTO userDTO,
                                @RequestParam("idUser") String idUser,
                                final RedirectAttributes redirectAttributes) {
        UserDTO updateUser = userService.update(userDTO, idUser);
        redirectAttributes.addFlashAttribute("updateUser", updateUser);
        return "redirect:/profile";
    }

    @PostMapping("/update-password")
    public RedirectView updatePasswordUser(@RequestParam("idUser") List<String> idUser,
                                           final RedirectAttributes redirectAttributes) {
        List<UserDTO> updatePasswordUserDTO = userService.updatePasswordUser(idUser);
        redirectAttributes.addFlashAttribute("updatePasswordUserDTO", updatePasswordUserDTO);

        return new RedirectView("profile");
    }
}
