package com.sevenklick.common.configuration.security.repository;

import com.sevenklick.common.configuration.security.domain.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by pierre.petersson
 */

@Repository
public interface AuthenticationRepository extends JpaRepository<UserSecurity, Long> {
  @Query("select u from UserSecurity  u where u.username =:username")
  UserSecurity find(@Param("username") String username);

}
