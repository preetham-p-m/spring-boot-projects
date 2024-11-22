package com.pmp.restful_web_service.service.Implementation;

import java.util.List;
import org.springframework.stereotype.Service;
import com.pmp.restful_web_service.exception.errors.UserNotFoundException;
import com.pmp.restful_web_service.model.AppUser;
import com.pmp.restful_web_service.repository.AppUserRepository;
import com.pmp.restful_web_service.service.interfaces.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository userRepository;

    public AppUserServiceImpl(AppUserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    /**
     * @return List<UserDetails>
     */
    @Override
    public List<AppUser> getAllUsers() {
        return this.userRepository.findAll();
    }

    /**
     * @param id
     * @return UserDetails
     */
    @Override
    public AppUser getUserById(long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User " + id + " not found."));
    }

    /**
     * @param user
     * @return UserDetails
     */
    @Override
    public AppUser createUser(AppUser user) {
        return this.userRepository.save(user);
    }

    /**
     * @param id
     */
    @Override
    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }

}
