package com.sevenklick.common.core.domain;

import com.google.common.base.Objects;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;
import com.google.common.base.Objects;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Set;
/**
 * Created by pierre.petersson on 29/11/2014.
 */
public class UserRoleContext implements Serializable{
        private static final long serialVersionUID = 6874667425302308430L;

        private Long id;
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        private String rolename;

        //@OneToMany(cascade = CascadeType.ALL)

        private Set<UserContext> userRoles;

        private Set<PermissionContext> permissions;

    public UserRoleContext(Long id, String rolename) {
        this.id = id;
        this.rolename = rolename;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Set<UserContext> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserContext> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<PermissionContext> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionContext> permissions) {
        this.permissions = permissions;
    }
}
