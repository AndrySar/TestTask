package com.bostongene.service;

import com.bostongene.model.UserEntity;
import com.bostongene.model.UserProfile;

import java.util.List;

/**
 * Created by Andry on 29.05.17.
 */
public interface UserService {

    public List<UserProfile> findAll();
    public UserProfile save(UserProfile user);
    public UserProfile findByEmail(String email);
    public void delete(UserProfile user);
}
