package com.gmail.hvorostenko.service.converter;

import com.gmail.hvorostenko.repository.model.Role;
import com.gmail.hvorostenko.repository.model.RoleEnum;
import com.gmail.hvorostenko.repository.model.User;
import com.gmail.hvorostenko.service.model.UserDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class UserConvertor {

    public User profileConvert(User user, UserDTO userDTO) {
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.getUserInfo().setAddress(userDTO.getAddress());
        user.getUserInfo().setTelephone(userDTO.getTelephone());
        return user;
    }

    public User convert(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPatronymic(userDTO.getPatronymic());
        user.setEmail(userDTO.getEmail());
        if (!StringUtils.isBlank(userDTO.getRole())) {
            Role role = new Role();
            role.setName(RoleEnum.valueOf(userDTO.getRole()));
            user.setRole(role);
        }
        return user;
    }

    public List<UserDTO> convert(List<User> users) {
        return users.stream().map(user -> {
            return convert(user);
        }).collect(Collectors.toList());
    }

    public UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setPatronymic(user.getPatronymic());
        userDTO.setEmail(user.getEmail());
        if (user.getRole() != null) {
            String nameRole = user.getRole().getName().name();
            userDTO.setRole(nameRole);
        }
        if (user.getUserInfo() != null) {
            String address = user.getUserInfo().getAddress();
            userDTO.setAddress(address);
            String telephone = user.getUserInfo().getTelephone();
            userDTO.setTelephone(telephone);
        }
        return userDTO;
    }

    public Map<Long, Long> zipToMap(List<Long> keys, List<Long> values) {
        Iterator<Long> keyIter = keys.iterator();
        Iterator<Long> valIter = values.iterator();
        return IntStream.range(0, keys.size()).boxed()
                .collect(Collectors.toMap(_i -> keyIter.next(), _i -> valIter.next()));
    }
}
