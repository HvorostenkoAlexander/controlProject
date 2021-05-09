package com.gmail.hvorostenko.repository.impl;

import com.gmail.hvorostenko.repository.UserRepository;
import com.gmail.hvorostenko.repository.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.hvorostenko.repository.constant.ConstRepository.CONDITION_ZERO;
import static com.gmail.hvorostenko.repository.constant.ConstRepository.MAX_RESULTS_CONST;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<Long, User> implements UserRepository {

    @Override
    public User getUserByEmail(String email) {
        String queryString = "select u from User as u  join fetch u.role where u.email = :email";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("email", email);
        User user = (User) query.getSingleResult();
        return user;
    }

    @Override
    public List<User> findAllSortEmail(int startPosition) {
        String queryString = "select u from User as u join fetch u.role order by u.email asc";
        Query query = entityManager.createQuery(queryString);
        if (startPosition != CONDITION_ZERO) {
            int firstResult = (startPosition * MAX_RESULTS_CONST) - MAX_RESULTS_CONST;
            query.setFirstResult(firstResult);
        } else {
            query.setFirstResult(startPosition);
        }
        query.setMaxResults(MAX_RESULTS_CONST);
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    public int deleteUsers(List<String> idUsers, String name) {
        List<Long> id = idUsers.stream().map(Long::parseLong).collect(Collectors.toList());
        String queryString = "delete from User as u where u.id in :id and u.email not in:name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("id", id);
        query.setParameter("name", name);
        return query.executeUpdate();
    }

    @Override
    public int updateUsers(Long userId, Long roleId) {
        String queryString = "update User u set u.role.id = :roleId  where u.id = :userId";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("userId", userId);
        query.setParameter("roleId", roleId);
        return query.executeUpdate();
    }


}
