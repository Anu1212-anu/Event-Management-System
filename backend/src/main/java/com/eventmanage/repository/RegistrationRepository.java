package com.eventmanage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventmanage.entity.Event;
import com.eventmanage.entity.Registration;
import com.eventmanage.entity.User;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
	
	List<Registration> findByUser(User user);
	
	Optional<Registration> findByRegId(Long regId);
	
	 boolean existsByUserAndEvent(User user, Event event);

}
