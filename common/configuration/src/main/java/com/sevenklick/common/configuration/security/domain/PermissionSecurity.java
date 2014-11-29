package com.sevenklick.common.configuration.security.domain;

import com.google.common.base.Objects;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by pierre.petersson on 29/11/2014.
 */

@Entity
@Table(name = "permissions")
public class PermissionSecurity implements GrantedAuthority {

    private static final long serialVersionUID = -5404269148967698143L;

    private Long id;
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String permissionname;

    private Set<RoleSecurity> permRoles;


    @Basic
    @Column(name = "permissionname", length = 50)
    public String getAuthority() {
        return permissionname;
    }
    public void setAuthority(String permissionname) {
        this.permissionname = permissionname;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permissions",
            joinColumns        = {@JoinColumn(name = "permission_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    public Set<RoleSecurity> getPermRoles() {
        return permRoles;
    }

    public void setPermRoles(Set<RoleSecurity> permRoles) {
        this.permRoles = permRoles;
    }

    @Override
    public String toString() {
        return String.format("%s(id=%d, permissionname='%s')",
                this.getClass().getSimpleName(),
                this.getId(), this.getAuthority());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof RoleSecurity) {
            final PermissionSecurity other = (PermissionSecurity) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getAuthority(), other.getAuthority());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getAuthority());
    }
}