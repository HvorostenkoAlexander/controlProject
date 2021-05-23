package com.gmail.hvorostenko.service.converter;

import com.gmail.hvorostenko.repository.model.Role;
import com.gmail.hvorostenko.repository.model.RoleEnum;
import com.gmail.hvorostenko.service.model.RoleDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleConvertor {

    public Role convert(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(RoleEnum.valueOf(roleDTO.getName()));
        role.setDescription(roleDTO.getDescription());
        return role;
    }

    public List<RoleDTO> convert(List<Role> roles) {
        return roles.stream().map(role -> {
            return convert(role);
        }).collect(Collectors.toList());
    }

    public RoleDTO convert(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName().name());
        roleDTO.setDescription(role.getDescription());
        return roleDTO;
    }
}
