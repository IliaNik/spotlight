package com.spotlight.services.user;

import com.spotlight.dto.profile.ProfileDataDTO;
import com.spotlight.model.user.User;
import com.spotlight.model.user.UserData;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * @author IliaNik on 23.02.2017.
 */
public interface UserService extends UserDetailsService, SocialUserDetailsService {
    User save(User user);

    User get(Integer id);

    User get(ProfileDataDTO profileDataDTO);

    void delete(Integer userId);

    User getByEmail(String email);

    Boolean isUserExist(Integer userId);

    Boolean isUserExist(String username);

    void updateUserPassword(Integer userId, ProfileDataDTO profileDataDTO);

    void updateUserData(Integer userId, ProfileDataDTO profileDataDTO);

    UserData getUserData(Integer userId);

}
