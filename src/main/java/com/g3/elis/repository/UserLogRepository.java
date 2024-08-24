package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.g3.elis.model.UserLog;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Integer> {
//	List<UserLog> findByUser(User user);
//
//	UserLog findFirstByUserAndLogoutTimeIsNullOrderByLoginTimeDesc(User user);

    // Find the most recent login entry for the user, based on login time, that does not have a logout time.
	UserLog findTopByUserIdOrderByLoginTimeDesc(int userId);
	
	@Query("SELECT u FROM UserLog u WHERE u.loginTime >= :startOfWeek")
	List<UserLog> findLogsInLastWeek(@Param("startOfWeek") LocalDateTime startOfWeek);
	
    @Query("SELECT u FROM UserLog u WHERE u.loginTime >= :startOfMonth")
	List<UserLog> findLogsInLastMonth(@Param("startOfMonth")LocalDateTime startOfMonth);
    
    @Query("SELECT u FROM UserLog u WHERE u.loginTime >= :startOfYear")
	List<UserLog> findLogsInLastYear(@Param("startOfYear")  LocalDateTime startOfYear);
}
