package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g3.elis.model.User;
import com.g3.elis.model.UserLog;
import java.util.List;


public interface UserLogRepository extends JpaRepository<UserLog, Integer> {
//	List<UserLog> findByUser(User user);
//
//	UserLog findFirstByUserAndLogoutTimeIsNullOrderByLoginTimeDesc(User user);

    // Find the most recent login entry for the user, based on login time, that does not have a logout time.
	UserLog findTopByUserIdOrderByLoginTimeDesc(int userId);
}
