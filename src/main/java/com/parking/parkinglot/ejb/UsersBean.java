package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.UserDto;

import java.util.ArrayList;
import java.util.List;

import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UsersBean {
    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    private List<UserDto> copyUserstoDto(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> userDtos.add(new UserDto(user.getId(), user.getEmail(), user.getPassword(), user.getUsername())));
        return userDtos;
    }

    public List<UserDto> findAllUsers(){
        LOG.info("Find all users");
        try {
            TypedQuery<UserDto> query = entityManager.createQuery("SELECT u FROM User u", UserDto.class);
            List<UserDto> userDtos = query.getResultList();
            return query.getResultList();
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
}
