package com.gmail.hvorostenko.repository.impl;

import com.gmail.hvorostenko.repository.RoleRepository;
import com.gmail.hvorostenko.repository.model.Role;
import com.gmail.hvorostenko.repository.model.RoleEnum;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class RoleRepositoryImpl extends GenericRepositoryImpl<Long, Role> implements RoleRepository {
    @Override
    public Role findAllByName(String roleName) {
        String queryString = "select r from Role as r  where r.name = :name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", RoleEnum.valueOf(roleName));
        Role role = (Role) query.getSingleResult();
        return role;
    }
}
