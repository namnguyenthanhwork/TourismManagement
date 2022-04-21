package com.ou.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationSuccessHandlerConfig implements AuthenticationSuccessHandler {

    @Autowired
    private RedirectStrategy redirectStrategy;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    protected void handle(final HttpServletRequest request, final HttpServletResponse response,
                          final Authentication authentication) throws IOException {
        redirectStrategy.sendRedirect(request, response, determineTargetUrl(authentication));
    }

    protected String determineTargetUrl(final Authentication authentication) {
        Map<String, String> roleTargetUrlMap = new HashMap<>();
        roleTargetUrlMap.put("quan-tri-vien", "/quan-tri-vien/trang-chu");
        roleTargetUrlMap.put("nhan-vien", "/nhan-vien/trang-chu");
        roleTargetUrlMap.put("khach-hang", "/");

        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if (roleTargetUrlMap.containsKey(authorityName)) {
                return roleTargetUrlMap.get(authorityName);
            }
        }

        throw new IllegalStateException();
    }

    /**
     * Removes temporary authentication-related data which may have been stored in the session
     * during the authentication process.
     */
    protected final void clearAuthenticationAttributes(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
