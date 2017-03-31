package com.spotlight.services.user;

import com.spotlight.dto.profile.ProfileDataDTO;
import com.spotlight.model.user.User;
import com.spotlight.model.user.UserData;
import com.spotlight.repositories.user.UserDataRepository;
import com.spotlight.repositories.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
                /* Encode the password before saving the user. */
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User get(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User get(ProfileDataDTO profileDataDTO) {
        User user = new User();
        UserData userData = new UserData();

        user.setEmail(profileDataDTO.getUsername());
        user.setPassword(passwordEncoder.encode(profileDataDTO.getPassword()));

        if (profileDataDTO.getFirstName() != null) {
            userData.setFirstName(profileDataDTO.getFirstName());
        }
        if (profileDataDTO.getLastName() != null) {
            userData.setLastName(profileDataDTO.getLastName());
        }
        if (profileDataDTO.getPhoto() != null) {
            userData.setPhoto(profileDataDTO.getPhoto());
        }
        if (profileDataDTO.getCity() != null) {
            userData.setCity(profileDataDTO.getCity());
        }
        user.setUserData(userData);
        return  user;
    }

    @Override
    public void delete(Integer userId) {
        userRepository.delete(userId);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Boolean isUserExist(Integer userId) {
        return userRepository.exists(userId);
    }

    @Override
    public Boolean isUserExist(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void updateUserData(Integer userId, ProfileDataDTO profileDataDTO) {
        UserData userData = userDataRepository.findById(userId);

        if (profileDataDTO.getFirstName() != null) {
            userData.setFirstName(profileDataDTO.getFirstName());
        }
        if (profileDataDTO.getLastName() != null) {
            userData.setLastName(profileDataDTO.getLastName());
        }
        if (profileDataDTO.getPhoto() != null) {
            userData.setPhoto(profileDataDTO.getPhoto());
        }
        if (profileDataDTO.getCity() != null) {
            userData.setCity(profileDataDTO.getCity());
        }

        userDataRepository.save(userData);
    }

    @Override
    public void updateUserPassword(Integer userId, ProfileDataDTO profileDataDTO) {
        User user = get(userId);
        String password = profileDataDTO.getPassword();
        if (user != null) {
            if (password != null) {
                user.setPassword(passwordEncoder.encode(password));
            }
            save(user);
        }
    }

    @Override
    public UserData getUserData(Integer userId) {
        return userDataRepository.findById(userId);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return loadUserByUserId(email);
    }

    @Override
    public User loadUserByUserId(String email) throws UsernameNotFoundException {
        final User user = getByEmail(email);
        if (user == null) {
            LOG.info("User {} not found", email);
            throw new UsernameNotFoundException("Email not found");
        }
        return user;
    }
}
