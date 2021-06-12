package com.gmail.hvorostenko.service.converter;

import com.gmail.hvorostenko.repository.model.Role;
import com.gmail.hvorostenko.repository.model.RoleEnum;
import com.gmail.hvorostenko.repository.model.User;
import com.gmail.hvorostenko.repository.model.UserInfo;
import com.gmail.hvorostenko.service.model.ProfileDTO;
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

    public ProfileDTO userConvert(UserDTO user) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(user.getId());
        profileDTO.setName(user.getName());
        profileDTO.setSurname(user.getSurname());
        profileDTO.setAddress(user.getAddress());
        profileDTO.setTelephone(user.getTelephone());
        return profileDTO;
    }

    public User profileConvert(User user, ProfileDTO userDTO) {
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        UserInfo userInfo = new UserInfo();
        userInfo.setAddress(userDTO.getAddress());
        userInfo.setTelephone(userDTO.getTelephone());
        userInfo.setUserInfoId(user.getId());
        user.setUserInfo(userInfo);
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
