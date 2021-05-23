package com.gmail.hvorostenko.web.controller;

import com.gmail.hvorostenko.repository.model.User;
import com.gmail.hvorostenko.service.PageService;
import com.gmail.hvorostenko.service.RoleService;
import com.gmail.hvorostenko.service.UserService;
import com.gmail.hvorostenko.service.model.PageDTO;
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
@RequestMapping("/administrator")
@RequiredArgsConstructor
public class AdministratorController {

    private final UserService userService;
    private final RoleService roleService;


    @GetMapping("/users")
    public String getUsersPage(Model model, @RequestParam(name = "page", defaultValue = "1") Integer pageCurrent) {
        PageDTO<UserDTO> pageDTO = userService.findAllSortEmail(pageCurrent);
        model.addAttribute("page", pageDTO);
        List<RoleDTO> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "users";
    }

    @PostMapping("/users-delete")
    public RedirectView deleteUser(@RequestParam("idCheckedDelete") List<String> idUsers,
                                   Principal principal,
                                   final RedirectAttributes redirectAttributes) {
        String nameUser = principal.getName();
        if (!idUsers.isEmpty()) {
            Integer usersCountDelete = userService.deleteUsers(idUsers, nameUser);
            redirectAttributes.addFlashAttribute("usersCountDelete", usersCountDelete);
        }
        return new RedirectView("users");
    }

    @PostMapping("/user-update-role")
    public RedirectView updateRoleUser(@RequestParam("roleName") List<String> roleName,
                                       @RequestParam("idUser") List<String> idUser,
                                       final RedirectAttributes redirectAttributes) {
        if (!roleName.isEmpty() && !idUser.isEmpty()) {
            Integer usersCountUpdate = userService.updateUsersRole(roleName, idUser);
            redirectAttributes.addFlashAttribute("usersCountUpdate", usersCountUpdate);
        }
        return new RedirectView("users");
    }

    @PostMapping("/user-update-password")
    public RedirectView updatePasswordUser(@RequestParam("idCheckedUpdatePassword") List<String> idUsers,
                                           final RedirectAttributes redirectAttributes) {
        if (!idUsers.isEmpty()) {
            List<UserDTO> updatePasswordUserDTO = userService.updatePasswordUser(idUsers);
            redirectAttributes.addFlashAttribute("updatePasswordUserDTO", updatePasswordUserDTO);
        }
        return new RedirectView("users");
    }

    @GetMapping("/user-add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        List<RoleDTO> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "user_add";
    }

    @PostMapping("/user-add")
    public String addUserSubmit(@ModelAttribute("user") @Valid UserDTO userDTO,
                                @RequestParam("idRole") String idRole,
                                BindingResult bindingResult,
                                final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "user_add";
        }
        UserDTO addUser = userService.add(userDTO, idRole);
        redirectAttributes.addFlashAttribute("addUser", addUser);
        return "redirect:/administrator/users";
    }
}
