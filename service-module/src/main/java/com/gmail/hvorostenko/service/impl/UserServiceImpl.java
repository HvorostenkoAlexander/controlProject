package com.gmail.hvorostenko.service.impl;

import com.gmail.hvorostenko.repository.RoleRepository;
import com.gmail.hvorostenko.repository.UserRepository;
import com.gmail.hvorostenko.repository.model.Role;
import com.gmail.hvorostenko.repository.model.User;
import com.gmail.hvorostenko.service.PageService;
import com.gmail.hvorostenko.service.UserService;
import com.gmail.hvorostenko.service.converter.UserConvertor;
import com.gmail.hvorostenko.service.email.MailSender;
import com.gmail.hvorostenko.service.model.PageDTO;
import com.gmail.hvorostenko.service.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.gmail.hvorostenko.repository.constant.ConstRepository.CONDITION_ZERO;
import static com.gmail.hvorostenko.repository.constant.ConstRepository.LENGTH_OF_PASSWORD_GENERATION;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConvertor userConvertor;
    private final MailSender mailSender;
    private final RoleRepository roleRepository;
    private final PageService<User> pageService;


    @Override
    @Transactional
    public UserDTO add(UserDTO userDTO, String idRole) {
        User user = userConvertor.convert(userDTO);
        Role role = roleRepository.findById(Long.parseLong(idRole));
        user.setRole(role);
        userRepository.persist(user);
        return userDTO;
    }


    @Override
    @Transactional
    public PageDTO findAllSortEmail(Integer pageCurrent) {
        PageDTO<UserDTO> pageDTO = new PageDTO();
        List<User> users = userRepository.findAllSortEmail(pageCurrent);
        List<UserDTO> userDTO = userConvertor.convert(users);
        pageDTO.getEntityList().addAll(userDTO);
        List<Integer> pageNumbers = pageService.countPage(new User());
        pageDTO.getPageNumbers().addAll(pageNumbers);
        return pageDTO;
    }

    @Override
    @Transactional
    public int deleteUsers(List<String> idUsers, String name) {
        int resultDelete = userRepository.deleteUsers(idUsers, name);
        return resultDelete;
    }

    @Override
    @Transactional
    public int updateUsersRole(List<String> roleNames, List<String> userId) {
        List<Long> roleId = new ArrayList<>();
        for (String roleName : roleNames) {
            Role role = roleRepository.findAllByName(roleName);
            roleId.add(role.getId());
        }
        List<Long> usersId = userId.stream().map(Long::parseLong).collect(Collectors.toList());
        Map<Long, Long> nameRoles = userConvertor.zipToMap(usersId, roleId);
        int resultUpdate = CONDITION_ZERO;
        for (Map.Entry<Long, Long> nameRole : nameRoles.entrySet()) {
            resultUpdate = +userRepository.updateUsers(nameRole.getKey(), nameRole.getValue());
        }
        return resultUpdate;
    }

    @Override
    @Transactional
    public List<UserDTO> updatePasswordUser(List<String> idUsers) {
        List<Long> usersId = idUsers.stream().map(Long::parseLong).collect(Collectors.toList());
        List<User> users = new ArrayList<>();
        for (Long id : usersId) {
            User user = userRepository.findById(id);
            String password = generateCommonLangPassword();
            user.setPassword(password);
            sendMessage(user);
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            users.add(userRepository.merge(user));
        }
        return userConvertor.convert(users);
    }

    @Override
    public String generateCommonLangPassword() {
        String password = RandomStringUtils.randomAlphanumeric(LENGTH_OF_PASSWORD_GENERATION);
        return password;
    }

    @Override
    public void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "your password - %s",
                    user.getName(),
                    user.getPassword()
            );
            mailSender.send(user.getEmail(), "Password update", message);
        }
    }

    @Override
    @Transactional
    public UserDTO getUserByEmail(String nameUser) {
        User user = userRepository.getUserByEmail(nameUser);
        return userConvertor.convert(user);
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO userDTO, String idUser) {
        User user = userRepository.findById(Long.parseLong(idUser));
        user = userConvertor.profileConvert(user, userDTO);
        userRepository.merge(user);
        return userConvertor.convert(user);
    }
}
