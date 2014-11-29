package com.sevenklick.common.configuration.security.domain;

import com.google.common.base.Objects;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ROLES")
public class RoleSecurity implements Serializable, GrantedAuthority  {

    private static final long serialVersionUID = 6874667425302308430L;

    private Long id;
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "rolename", length = 50)
    private String rolename;

    //@OneToMany(cascade = CascadeType.ALL)

    private Set<UserSecurity> userRoles;

    private Set<PermissionSecurity> permissions;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns        = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
    )
    public Set<UserSecurity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserSecurity> userRoles) {
        this.userRoles = userRoles;
    }
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permissions",
            joinColumns        = { @JoinColumn(name = "role_id",       referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "permission_id", referencedColumnName = "id") }
    )
    public Set<PermissionSecurity> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionSecurity> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return String.format("%s(id=%d, rolename='%s')",
                this.getClass().getSimpleName(),
                this.getId(), this.getRolename());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof RoleSecurity) {
            final RoleSecurity other = (RoleSecurity) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getRolename(), other.getRolename());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getRolename());
    }

    @Override
    @Transient
    public String getAuthority() {
        return getRolename();
    }
    @Transient
    public void setAuthority(String authority) {
        this.rolename = authority;
    }
}