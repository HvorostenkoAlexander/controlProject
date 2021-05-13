package com.gmail.hvorostenko.service;

import com.gmail.hvorostenko.repository.model.User;
import com.gmail.hvorostenko.service.model.PageDTO;
import com.gmail.hvorostenko.service.model.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO add(UserDTO userDTO, String idRole);
    PageDTO findAllSortEmail(Integer pageCurrent);
    int deleteUsers(List<String> idUsers, String name);
    int updateUsersRole(List<String> selectRole, List<String> userId);
    List<UserDTO> updatePasswordUser(List<String> idUsers);
    String generateCommonLangPassword();
    void sendMessage(User user);
    UserDTO getUserByEmail(String nameUser);
    UserDTO update(UserDTO userDTO, String idUser);
}
