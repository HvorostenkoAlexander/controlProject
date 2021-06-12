package com.gmail.hvorostenko.web.controller;

import com.gmail.hvorostenko.service.UserService;
import com.gmail.hvorostenko.service.converter.UserConvertor;
import com.gmail.hvorostenko.service.model.ArticleDTO;
import com.gmail.hvorostenko.service.model.ProfileDTO;
import com.gmail.hvorostenko.service.model.UserDTO;
import liquibase.pro.packaged.E;
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
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final UserConvertor userConvertor;

    @GetMapping
    public String getProfileForm(Model model,
                                 Principal principal) {
        String nameUser = principal.getName();
        UserDTO user = userService.getUserByEmail(nameUser);
        ProfileDTO profileDTO = userConvertor.userConvert(user);
        model.addAttribute("profile", profileDTO);
        return "profile";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("profile") ProfileDTO profileDTO,
                             BindingResult bindingResult,
                             @RequestParam("idUser") String idUser,
                             final RedirectAttributes redirectAttributes) throws Exception {
        if (bindingResult.hasErrors()) {
            return "redirect:/profile";
        }
        UserDTO updateUser = userService.update(profileDTO, idUser);
        redirectAttributes.addFlashAttribute("updateUser", updateUser);
        return "redirect:/articles";
    }

    @PostMapping("/update-password")
    public RedirectView updatePasswordUser(@RequestParam("idUser") List<String> idUser,
                                           final RedirectAttributes redirectAttributes) {
        List<UserDTO> updatePasswordUserDTO = userService.updatePasswordUser(idUser);
        redirectAttributes.addFlashAttribute("updatePasswordUserDTO", updatePasswordUserDTO);

        return new RedirectView("");
    }
}
