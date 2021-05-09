package com.gmail.hvorostenko.repository;

import com.gmail.hvorostenko.repository.model.Role;

public interface RoleRepository extends GenericRepository<Long, Role>{
    Role findAllByName(String roleName);
}
