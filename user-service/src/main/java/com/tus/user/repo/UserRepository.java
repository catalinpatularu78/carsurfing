
package com.tus.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tus.user.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "select * from user u where u.phone = :phone", nativeQuery = true)
	User findUser(@Param("phone") String phone);
	
	Optional<User> findById(Integer id);

}
