package com.spotlight.web.user;

import com.spotlight.dto.error.ErrorDTO;
import com.spotlight.dto.profile.ProfileDataDTO;
import com.spotlight.model.user.User;
import com.spotlight.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import static com.spotlight.util.SecurityUtil.currentUser;

/**
 * @author IliaNik on 18.03.2017.
 */

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user,
                                        UriComponentsBuilder ucBuilder) {

        LOG.info(user.toString());

        final String username = user.getUsername();
        if (userService.isUserExist(username)) {
            LOG.error("Unable to create. A User with name {} already exist", username);
            return new ResponseEntity<>(new ErrorDTO("Unable to create",
                    " A User with name " + username + " already exist."),HttpStatus.CONFLICT);
        }

        final Integer userId = userService.save(user).getId();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(userId).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity<?> getCurrentUser() {
        final User currentUser = currentUser();
        if (currentUser == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") Integer id) {
        final User user = userService.get(id);
        if (user == null) {
            LOG.error("User with id {} not found.", id);
            return new ResponseEntity<>(new ErrorDTO("Unable to get",
                    "User with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id,
                                        @Valid @RequestBody ProfileDataDTO profileDataDTO) {
        final User user = userService.get(id);
        if (user == null) {
            LOG.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity<>(new ErrorDTO("Unable to update:",
                    " User with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }
        userService.updateUserData(id, profileDataDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        if (userService.isUserExist(id)) {
            LOG.error("Unable to delete. User with id {} not found.", id);
            return new ResponseEntity<>(new ErrorDTO("Unable to Delete",
                    " User with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
