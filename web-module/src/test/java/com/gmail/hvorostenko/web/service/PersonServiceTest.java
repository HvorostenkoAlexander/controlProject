package com.gmail.hvorostenko.web.service;

import com.gmail.hvorostenko.repository.RoleRepository;
import com.gmail.hvorostenko.repository.UserRepository;
import com.gmail.hvorostenko.repository.model.Role;
import com.gmail.hvorostenko.repository.model.RoleEnum;
import com.gmail.hvorostenko.repository.model.User;
import com.gmail.hvorostenko.service.converter.UserConvertor;
import com.gmail.hvorostenko.service.impl.UserServiceImpl;
import com.gmail.hvorostenko.service.model.ProfileDTO;
import com.gmail.hvorostenko.service.model.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserConvertor userConvertor;

    @Mock
    private RoleRepository roleRepository;


    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldDeleteUsers() {
        String name = "test@gmail.com";
        List<String> idUsers = Collections.emptyList();
        int resultDelete = userService.deleteUsers(idUsers, name);
        assertEquals(0, resultDelete);
    }

    @Test
    void shouldUpdateUsers() {
        String id = "1";
        String name = "name";

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setName(name);

        User user = new User();
        user.setId(Long.parseLong(id));
        user.setName(name);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(Long.parseLong(id));
        userDTO.setName(name);

        when(userRepository.findById(Long.parseLong(id))).thenReturn(user);
        when(userConvertor.profileConvert(user, profileDTO)).thenReturn(user);
        when(userConvertor.convert(user)).thenReturn(userDTO);

        UserDTO userDTOResult = userService.update(profileDTO, id);
        assertEquals(name, user.getName());
        assertEquals(name, userDTOResult.getName());
    }

    @Test
    void shouldGetUserByEmail() {
        String email = "test@gmail.com";

        User user = new User();
        user.setEmail(email);

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());

        when(userRepository.getUserByEmail(email)).thenReturn(user);
        when(userConvertor.convert(user)).thenReturn(userDTO);

        UserDTO userDTOResult = userService.getUserByEmail(email);
        assertEquals(email, userDTOResult.getEmail());
    }

    @Test
    void shouldAddNewUser() {
        long id = 1L;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setName("name");
        userDTO.setSurname("surname");
        userDTO.setPatronymic("Patronymic");
        userDTO.setEmail("test@gmail.com");
        userDTO.setTelephone("375290000000");
        userDTO.setAddress("minsk");
        userDTO.setRole("ADMINISTRATOR");

        Role role = new Role();
        role.setId(id);
        role.setName(RoleEnum.valueOf("ADMINISTRATOR"));
        when(roleRepository.findById(id)).thenReturn(role);

        User user = new User();
        user.setId(id);
        user.setRole(role);
        when(userConvertor.convert(userDTO)).thenReturn(user);

        UserDTO userDTOResult = userService.add(userDTO, String.valueOf(id));

        assertEquals(id, userDTOResult.getId());
        assertEquals(role.getName().name(), userDTOResult.getRole());
    }
}
