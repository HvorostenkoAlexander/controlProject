package com.gmail.hvorostenko.repository;

import com.gmail.hvorostenko.repository.model.User;

import java.util.List;

public interface UserRepository extends GenericRepository<Long, User> {
    User getUserByEmail(String email);
    List<User> findAllSortEmail(int startPosition);
    int deleteUsers(List<String> idUsers, String name);
    int updateUsers(Long userId, Long roleId);
}
