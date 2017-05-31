package com.bostongene.service.impl;

import com.bostongene.exception.ServiceException;
import com.bostongene.exception.UserExistsException;
import com.bostongene.model.UserEntity;
import com.bostongene.model.UserProfile;
import com.bostongene.service.UserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Andry on 29.05.17.
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<UserProfile> findAll() {
        return em.createQuery("select u from UserEntity u", UserEntity.class)
                .getResultList()
                .stream()
                .map(UserEntity::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserProfile findByEmail(String email) {
        try {
            return ((UserEntity) em.createQuery("SELECT u FROM UserEntity u WHERE u.email = :custEmail")
                    .setParameter("custEmail", email)
                    .setMaxResults(1).getSingleResult()).toDto();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public UserProfile save(UserProfile user) throws UserExistsException, ServiceException {
        try {
            UserEntity entity = new UserEntity(
                    user.getSurname(),
                    user.getUsername(),
                    user.getDateOfBirth(),
                    user.getEmail(),
                    user.getPassword());

            em.persist(entity);
            return entity.toDto();
        }catch (javax.persistence.PersistenceException e) {

            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                org.hibernate.exception.ConstraintViolationException exp = (org.hibernate.exception.ConstraintViolationException)e.getCause();

                // Identify exception type
                if(exp.getErrorCode() == 23505)
                    throw new UserExistsException(e);
                else
                    throw new ServiceException(e);
            } else {
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public void delete(UserProfile user) throws ServiceException {
        try {
            em.createQuery("DELETE FROM UserEntity u WHERE u.email = :custEmail")
                    .setParameter("custEmail", user.getEmail()).executeUpdate();
        }catch (javax.persistence.PersistenceException e) {
                throw new ServiceException(e);
        }
   }


}
