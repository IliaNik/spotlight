package com.spotlight.util;

import com.spotlight.model.user.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Andrey on 7/22/16.
 */
public final class SecurityUtil {

    private SecurityUtil() {
    }

    public static Integer currentUserId() {
        final User user = currentUser();
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    public static User currentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return (User) authentication.getPrincipal();
    }

}