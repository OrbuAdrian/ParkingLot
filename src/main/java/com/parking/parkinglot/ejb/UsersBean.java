package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.UserDto;

import java.util.*;

import com.parking.parkinglot.entities.User;
import com.parking.parkinglot.entities.UserGroup;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.logging.Logger;

@Stateless
public class UsersBean {
    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    PasswordBean passwordBean;

    private List<UserDto> copyUsersToDto(List<User> users) {
        List<UserDto> userDto = new ArrayList<>();
        users.forEach(user -> userDto.add(new UserDto(user.getId(), user.getEmail(), user.getPassword(), user.getUsername(), user.getAge())));
        return userDto;
    }

    public List<UserDto> findAllUsers(){
        LOG.info("Find all users");
        List<UserDto> dtos = new ArrayList<>();
        try {
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users =  query.getResultList();
            dtos.addAll(copyUsersToDto(users));
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
        return dtos;
    }

    public void createUser(String email, String password, String username) {
        LOG.info("Creating user");

        try {

            Random random = new Random();
            int age = random.nextInt(83) + 18;

            // Cream un obiect User
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setUsername(username);
            user.setAge(age);


            entityManager.persist(user);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private void assignGroupsToUser(String username, Collection<String> groups) {
        LOG.info("assignGroupsToUser");
        for (String group : groups) {
            UserGroup userGroup = new UserGroup();
            userGroup.setUsername(username);
            userGroup.setUserGroup(group);
            entityManager.persist(userGroup);
    }

    }
}
