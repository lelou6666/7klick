package com.sevenklick.profile.backend.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by pierre.petersson on 25/10/2014.
 */
@Entity
@Table(name = "user", schema = "public", catalog = "postgres")
public class UserEntity {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Integer yearOfBirth;
    private String phoneNumber;
    private String password;
    private Timestamp lastUpdated;
    private Timestamp registered;
    private Timestamp lastLogin;
    private List<CvEntity> cvEntities=new ArrayList<CvEntity>();

    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true)
    @SequenceGenerator(name = "profile_sequence", sequenceName = "profile_sequence", schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_sequence")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "first_name", nullable = true, insertable = true, updatable = true, length = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, insertable = true, updatable = true, length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "year_of_birth", nullable = true, insertable = true, updatable = true)
    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Basic
    @Column(name = "phone_number", nullable = true, insertable = true, updatable = true, length = 50)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "password", nullable = true, insertable = true, updatable = true, length = 200)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "last_updated", nullable = true, insertable = true, updatable = true)
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Basic
    @Column(name = "registered", nullable = true, insertable = true, updatable = true)
    public Timestamp getRegistered() {
        return registered;
    }

    public void setRegistered(Timestamp registered) {
        this.registered = registered;
    }

    @Basic
    @Column(name = "last_login", nullable = true, insertable = true, updatable = true)
    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (lastLogin != null ? !lastLogin.equals(that.lastLogin) : that.lastLogin != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (lastUpdated != null ? !lastUpdated.equals(that.lastUpdated) : that.lastUpdated != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (registered != null ? !registered.equals(that.registered) : that.registered != null) return false;
        if (yearOfBirth != null ? !yearOfBirth.equals(that.yearOfBirth) : that.yearOfBirth != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (yearOfBirth != null ? yearOfBirth.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0);
        result = 31 * result + (registered != null ? registered.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        return result;
    }
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity",fetch = FetchType.EAGER)
    public List<CvEntity> getCvEntities() {
        return cvEntities;
    }

    public void setCvEntities(List<CvEntity> cvEntities) {
        this.cvEntities = cvEntities;
    }


    public void addCV(CvEntity cvEntity) {
        this.cvEntities.add(cvEntity);
        cvEntity.setUserEntity(this);
    }
}
