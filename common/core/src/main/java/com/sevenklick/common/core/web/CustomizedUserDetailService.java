package com.sevenklick.common.core.web;

import com.sevenklick.common.configuration.security.domain.RoleSecurity;
import com.sevenklick.common.configuration.security.domain.UserSecurity;
import com.sevenklick.common.configuration.security.repository.AuthenticationRepository;
import com.sevenklick.common.core.domain.UserContext;
import com.sevenklick.common.core.domain.UserRoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import javax.sql.DataSource;
import java.net.URI;
import java.util.*;

/**
 * Created by pierre.petersson on 28/11/2014.
 */

public class CustomizedUserDetailService implements UserDetailsService {
    @Autowired
    AuthenticationRepository authenticationRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserSecurity userSecurity = authenticationRepository.find(userName);
        List<GrantedAuthority> authorities = buildUserAuthority(userSecurity.getAuthorities());
        return buildUserForAuthentication( userSecurity, authorities);
    }

    private User buildUserForAuthentication(UserSecurity user,
                                            List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Collection<GrantedAuthority> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        for (GrantedAuthority userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getAuthority()));
        }
        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
                setAuths);
        return Result;
    }
}
