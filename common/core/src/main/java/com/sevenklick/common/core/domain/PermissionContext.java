package com.sevenklick.common.core.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pierre.petersson on 26/05/2014.
 */
public class PermissionContext implements UserDetails {
        public Long getPlcId() {
            return plcId;
        }

        public void setPlcId(Long plcId) {
            this.plcId = plcId;
        }


        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getLangCode() {
            return langCode;
        }

        public void setLangCode(String langCode) {
            this.langCode = langCode;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getRoleCode() {
            return roleCode;
        }

        public void setRoleCode(String roleCode) {
            this.roleCode = roleCode;
        }

    public Boolean getAdminEnabled() {
        return adminEnabled;
    }

    public void setAdminEnabled(Boolean adminEnabled) {
        this.adminEnabled = adminEnabled;
    }
    private Set<UserRoleContext> userRole = new HashSet<UserRoleContext>(0);

    public Set<UserRoleContext> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRoleContext> userRole) {
        this.userRole = userRole;
    }

    Long plcId;
        String userName;
        String langCode;
        String countryCode;
        String roleCode;
        Boolean adminEnabled=false;
        String password;
    boolean enabled;

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
