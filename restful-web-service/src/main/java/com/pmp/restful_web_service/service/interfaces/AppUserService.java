package com.pmp.restful_web_service.service.interfaces;

import java.util.List;

import com.pmp.restful_web_service.model.AppUser;

public interface AppUserService {

    /**
     * @return List<User>
     */
    List<AppUser> getAllUsers();

    /**
     * @param id
     * @return User
     */
    AppUser getUserById(long id);

    /**
     * @param user
     * @return User
     */
    AppUser createUser(AppUser user);

    /**
     * @param id
     */
    void deleteUserById(long id);
}
