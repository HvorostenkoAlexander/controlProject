package com.gmail.hvorostenko.service.impl;

import com.gmail.hvorostenko.repository.RoleRepository;
import com.gmail.hvorostenko.repository.model.Role;
import com.gmail.hvorostenko.service.RoleService;
import com.gmail.hvorostenko.service.converter.RoleConvertor;
import com.gmail.hvorostenko.service.model.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleConvertor roleConvertor;
    private final RoleRepository roleRepository;

    @Override
    public List<RoleDTO> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roleConvertor.convert(roles);
    }
}
