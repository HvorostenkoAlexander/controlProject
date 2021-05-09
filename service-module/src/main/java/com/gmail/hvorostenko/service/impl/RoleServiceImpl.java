package com.gmail.hvorostenko.service.impl;

import com.gmail.hvorostenko.repository.RoleRepository;
import com.gmail.hvorostenko.repository.model.Role;
import com.gmail.hvorostenko.service.RoleService;
import com.gmail.hvorostenko.service.converter.RoleConvertor;
import com.gmail.hvorostenko.service.model.RoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleConvertor roleConvertor;
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleConvertor roleConvertor, RoleRepository roleRepository) {
        this.roleConvertor = roleConvertor;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDTO> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roleConvertor.convert(roles);
    }
}
