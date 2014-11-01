package com.sevenklick.profile.backend.repository;


import com.sevenklick.profile.backend.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lars.vateman on 2014-06-23.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  @Query("select u from UserEntity  u where u.email =:email")
  UserEntity isEmailUnique(@Param("email") String email);
  @Query("select u from UserEntity  u where u.email =:email and u.password=:password")
  UserEntity validateUserCredentials(@Param("email") String email, @Param("password") String password);

}
