package com.sevenklick.common.configuration.security.domain;

import com.google.common.base.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pierre.petersson on 29/11/2014.
 */

@Entity
@Table(name="users")
public class UserSecurity implements UserDetails {

    private static final long serialVersionUID = 6311364761937265306L;
    private Long id;
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "password", length = 120)
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    private Set<RoleSecurity> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns        = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    public Set<RoleSecurity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleSecurity> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return String.format("%s(id=%d, username=%s, password=%s, enabled=%b)",
                this.getClass().getSimpleName(),
                this.getId(),
                this.getUsername(),
                this.getPassword(),
                this.getEnabled());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof UserSecurity) {
            final UserSecurity other = (UserSecurity) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getUsername(), other.getUsername())
                    && Objects.equal(getPassword(), other.getPassword())
                    && Objects.equal(getEnabled(), other.getEnabled());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getUsername(), getPassword(), getEnabled());
    }

    @Transient
    public Set<PermissionSecurity> getPermissions() {
        Set<PermissionSecurity> perms = new HashSet<PermissionSecurity>();
        for (RoleSecurity role : roles) {
            perms.addAll(role.getPermissions());
        }
        return perms;
    }

    @Override
    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.addAll(getRoles());
        authorities.addAll(getPermissions());
        return authorities;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        //return true = account is valid / not expired
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        //return true = account is not locked
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        //return true = password is valid / not expired
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return this.getEnabled();
    }

}