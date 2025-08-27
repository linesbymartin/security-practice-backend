package com.linesbymartin.securitypractice.ultils;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import com.linesbymartin.securitypractice.user.domain.UserRole;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Claims;

import java.util.Objects;
import java.util.UUID;

public class ClaimsUtil {
    public static Claims getCurrentClaims() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getCredentials() instanceof Claims claims) {
            return claims;
        }
        throw new AuthenticationCredentialsNotFoundException("Claims not available.");
    }


    public static UUID getCurrentUserId() {
        Claims claims = getCurrentClaims();
        return UUID.fromString(Objects.requireNonNull(claims).get("userId").toString());
    }

    public static UserRole getCurrentUserRole() {
        Claims claims = getCurrentClaims();
        return UserRole.valueOf(Objects.requireNonNull(claims).get("role").toString());
    }

    public static String getCurrentUserEmail() {
        Claims claims = getCurrentClaims();
        return Objects.requireNonNull(claims).get("sub").toString();
    }
}
